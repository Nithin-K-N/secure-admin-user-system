"use client";

import { useAuthGuard } from '@/hooks/useAuthGuard'
import { useRouter } from 'next/navigation';
import React from 'react'

function Page() {
  useAuthGuard("ADMIN");

  const router = useRouter();
  const handleClick = (route: string) => {
    router.push(route);
  }

  return (
    <div className="flex flex-col text-black h-full w-full items-center justify-center">
      <h1 className='bg-gray-200 m-2 p-2 rounded-2xl'>Admin Page</h1>
      <p className='text-white'>Welcome! You are logged in as an admin(admin only access).</p>
      <div className='flex flex-row gap-2 p-2'>
        <button 
          className='bg-blue-500 text-white p-2 rounded-lg mt-4 hover:bg-blue-600'
          onClick={() => handleClick('/messages/inbox')}
          >
          Inbox
        </button>
        <button 
          className='bg-blue-500 text-white p-2 rounded-lg mt-4 hover:bg-blue-600'
          onClick={() => handleClick('/messages/sent')}
          >
          Sent
        </button>
        <button 
          className='bg-green-500 text-white p-2 rounded-lg mt-4 hover:bg-green-600'
          onClick={() => handleClick('/messages/send')}
          >
          Send Message
        </button>
      </div>
    </div>
  )
}

export default Page