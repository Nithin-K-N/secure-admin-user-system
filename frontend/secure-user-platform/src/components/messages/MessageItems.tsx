import { Message } from '@/lib/types/messages/Messages'

function MessageItems({message}:{message : Message}) {
  return (
    <div>
        <h3>{new Date(message.createdAt).toLocaleString()}</h3>
        <p>{message.content}</p>
        <p>From: {message.senderUsername}</p>
        <p>To: {message.receiverUsername}</p>
    </div>
  )
}

export default MessageItems