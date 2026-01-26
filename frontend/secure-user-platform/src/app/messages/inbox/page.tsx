'use client';

import MessageList from "@/components/messages/MessageList";
import { message_viewType } from "@/lib/constants/stringContants";


function Page() {

  return (
    <MessageList viewType={message_viewType.INBOX} />
  ) 
}

export default Page