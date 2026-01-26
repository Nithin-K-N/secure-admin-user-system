'use client';

import { SendMessageRequest } from '@/lib/types/messages/SendMessageRequest';
import { sendMessage } from '@/services/message.service';
import { useForm } from 'react-hook-form';


function SendMessagesForm() {
    const { 
        register, 
        handleSubmit, 
        formState: { errors, isSubmitting },
        reset,
    } = useForm<SendMessageRequest>(
        {
            defaultValues: {
                receiverId: 0,
                message: ''
            }
        }
    );

    const onSubmit = async (data: SendMessageRequest ) => {
        await sendMessage({
                receiverId: Number(data.receiverId),
                message: data.message,
        });
        reset();
        alert("Message sent");
    }

    return (
        <form 
            onSubmit={handleSubmit(onSubmit)}
            className='flex flex-col gap-4 p-4 max-w-md mx-auto'
        >
            <input
                type="text"
                placeholder="Receiver ID"
                {...register('receiverId', { 
                    required: "Receiver is required", 
                    pattern: {
                        value: /^[0-9]+$/,
                        message: "Receiver ID must be a number",
                    },
                })}
            />
            {errors.receiverId && <span>{errors.receiverId.message}</span>}

            <textarea
                placeholder="Message Content"
                {...register('message', { 
                    required: "Message cannot be empty",
                    minLength: {
                        value: 5,
                        message: "Message must be at least 5 characters",
                    },
                    maxLength: {
                        value: 2000,
                        message: "Message too long",
                    }, 
                })}
            />
            {errors.message && <span>{errors.message.message}</span>}

            <button className='text-white bg-blue-500 hover:bg-blue-800 p-2 rounded-b-md' type="submit" disabled={isSubmitting}> Send </button>
        </form>
    )
}

export default SendMessagesForm