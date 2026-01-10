import { LOGIN_ENDPOINT, REGISTER_ENDPOINT } from "@/lib/constants/apiEndpoints"
import { apiFetch } from "./api"

export async function registerApi(
    data:{
        username: string,
        firstName: string,
        lastName: string,
        email: string,
        password: string
    }
) {
    const response = await apiFetch(REGISTER_ENDPOINT,{
        method: 'POST',
        body: JSON.stringify(data)
    })
    
    if(!response.ok) throw new Error('Registration failed');
};

export async function loginApi(
    identifier: string,
    password: string
) {
    const response = await apiFetch(LOGIN_ENDPOINT,{
        method: 'POST',
        body: JSON.stringify({ identifier, password })
    })

    if(!response.ok) throw new Error('Login failed');

    return response.text();
};