import { MESSAGE_INBOX_ENDPOINT, MESSAGE_SEND_ENDPOINT, MESSAGE_SENT_ENDPOINT } from "@/lib/constants/apiEndpoints";
import { apiFetch } from "./api";
import { SendMessageRequest } from "@/lib/types/messages/SendMessageRequest";

export async function getInbox(){
    const Response = await apiFetch(MESSAGE_INBOX_ENDPOINT, {method: 'GET'});
    if(!Response.ok)  throw new Error('Failed to fetch sent messages');
    return Response.json();
}

export async function getSentMessages(){
    const Response = await apiFetch(MESSAGE_SENT_ENDPOINT, {method: 'GET'});
    if(!Response.ok)  throw new Error('Failed to fetch sent messages');
    return Response.json();
}

export async function sendMessage(data: SendMessageRequest ): Promise<void> {
    const Response = await apiFetch(MESSAGE_SEND_ENDPOINT, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if(!Response.ok)  throw new Error('Failed to send message');
}