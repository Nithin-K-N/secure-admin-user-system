"use client";

import { useAuthStore } from "@/store/auth.store";
import { useRouter } from "next/navigation";
import { useEffect } from "react";


export function useAuthGuard(
    requiredRole: "USER" | "ADMIN"
) {
    const router = useRouter();
    const { token, role } = useAuthStore();

    console.log("useAuthGuard - token:", token, "role:", role, "requiredRole:", requiredRole);
    useEffect(()=>{
        if(!token){
            router.push('/login');
            return;
        }
        
        if(role !== requiredRole){
            router.replace('/login')
        }
    },[router, token, role, requiredRole]);

};