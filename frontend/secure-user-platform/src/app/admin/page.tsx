"use client";

import { useAuthGuard } from '@/hooks/useAuthGuard'
import React from 'react'

function Page() {
  useAuthGuard("ADMIN");

  return (
    <div className="flex flex-col text-black h-full w-full items-center justify-center">
      <h1 className='bg-gray-200 m-2 p-2 rounded-2xl'>Admin Page</h1>
      <p className='text-white'>Welcome! You are logged in as an admin(admin only access).</p>
    </div>
  )
}

export default Page