"use client";

import { loginApi } from '@/services/auth.service';
import { useAuthStore } from '@/store/auth.store';
import { useRouter } from 'next/navigation';
import React, { useState } from 'react'

function Page() {
    const router = useRouter();
    const login = useAuthStore(state => state.login);

    const [identifier, setIdentifier] = useState('');
    const [password, setPassword] = React.useState('');

    async function handleLogin() {
        try{
            if(identifier === "" || password === ""){
                alert("Please fill all fields");
                return;
            }
            const token = await loginApi(identifier, password);
            if(!token){
                alert("Invalid login response");
                return;
            }
            login(token, "USER");
            alert("Login successful");
            router.push('/user')
        }catch (error) {
            console.error("Login error:", error);
            alert("Login failed");
        }
    }

  return (
    <div className="flex bg-amber-50 text-black h-full w-full items-center justify-center">
      <h2>Login Page</h2>
      <form>
        <input
          type="text"
          placeholder="Identifier"
          value={identifier}
          onChange={(e) => setIdentifier(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button type="button" onClick={()=>handleLogin()}>Login</button>
      </form>
    </div>
  )
}

export default Page