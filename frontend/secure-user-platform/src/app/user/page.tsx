"use client";

import { useAuthGuard } from '@/hooks/useAuthGuard'
import React from 'react'

function Page() {
  useAuthGuard('USER')

  return (
    <div className="flex flex-col text-black h-full w-full items-center justify-center">
      <h1 className='bg-amber-50 m-2 p-2 rounded-2xl'>User Page</h1>
      <p className='text-white'>Welcome! You are logged in as a user.</p>
    </div>
  )
}

export default Page