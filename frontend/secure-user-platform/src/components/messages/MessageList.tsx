'use client';

import { message_viewType } from '@/lib/constants/stringContants';
import { Message } from '@/lib/types/messages/Messages';
import { getInbox, getSentMessages } from '@/services/message.service';
import { useEffect, useState } from 'react'
import MessageItems from './MessageItems';

function MessageList(
    { viewType }: { viewType: message_viewType }
) {
    const [loading, setLoading] = useState(true);
    const [ messageList, setMessageList ] = useState<Message[]>([]);
    const [ SelectedMessage, setSelectedMessage ] = useState<Message | null>(null);

    useEffect(() => {
        setLoading(true);

        const fetcher =
            viewType === message_viewType.INBOX 
            ? getInbox 
            : getSentMessages;

        fetcher()
            .then(data =>{ setMessageList(data.content ?? [])})
            .catch(console.error)
            .finally(() => setLoading(false));
        
    }, [viewType]);

    if (loading) return <div>Loading...</div>;

    return (
        <div className='flex flex-row min-h-full w-full'>
            <div className='flex flex-1 flex-col bg-gray-400 p-2 h-full overflow-y-auto'>
                {messageList.length === 0
                    ? (<div>No Messages found</div>)
                    : <ul>
                        { messageList.map((message) => (
                            <li
                                key={message.id} 
                                onClick={() => setSelectedMessage(message)}
                            >
                                { viewType === message_viewType.SENT 
                                    ? message.receiverUsername 
                                    : message.senderUsername
                                }
                            </li>
                        ))}
                    </ul>
                }
            </div>
            <div className='flex flex-3 p-2'>
                { SelectedMessage 
                    ?  <MessageItems message={SelectedMessage} />
                    :  <div>Select a message to view details</div> 
                }
            </div>
        </div>
    )
}

export default MessageList