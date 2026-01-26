import MessageList from '@/components/messages/MessageList'
import { message_viewType } from '@/lib/constants/stringContants'
import React from 'react'

function page() {
  return (
    <MessageList viewType={message_viewType.SENT} />
  )
}

export default page