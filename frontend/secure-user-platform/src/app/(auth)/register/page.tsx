"use client";

import { registerApi } from "@/services/auth.service";
import { useRouter } from "next/navigation";
import React, { useState } from "react";

type RegisterFormData = {
    username: string;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
};

function Page() {
    const router = useRouter();
    const [registerForm, setRegisterForm] = useState<RegisterFormData>({
        username: "",
        firstName: "",
        lastName: "",
        email: "",
        password: ""
    });

    async function handleRegister () {
        try{
            if(Object.values(registerForm).some(value => value === "")){
                alert("Please fill all fields");
                return;
            }
            await registerApi(registerForm);
            alert("Registration successful");
            router.push('/login')
        } catch (error) {
            console.error("Registration error:", error);
            alert("Registration failed");
        }    

    }

    return (
        <div className="flex flex-col h-full w-full items-center justify-center">
            <h2 className="flex bg-amber-50 text-black p-2 m-2 rounded-md">
                Register Page
            </h2>
            <form className="flex flex-col bg-amber-50 text-black items-center p-2 m-2 rounded-md">
                <div  className="grid grid-cols-2 p-2 space-x-2">
                    {Object.keys(registerForm).map(key => (
                        <div key={key}>
                            <input 
                            type={key === "password" ? "password" : "text"}
                            placeholder={key}
                            name={key} 
                            value={(registerForm)[key as keyof RegisterFormData]} 
                            onChange={(e) => setRegisterForm({
                                ...registerForm, 
                                [key]: e.target.value || ""}
                            )} 
                            />
                        </div>
                    ))}
                </div>
                <button 
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mt-1 rounded cursor-pointer" 
                    type="button" 
                    onClick={handleRegister}
                >
                    Register
                </button>
            </form>
        </div>
    );
}

export default Page;
