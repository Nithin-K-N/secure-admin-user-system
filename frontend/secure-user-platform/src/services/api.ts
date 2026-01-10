import { BASE_URL } from "@/lib/constants/apiEndpoints";

// Central API Client
export async function apiFetch(
    endpoint:string, 
    options: RequestInit = {}
) {
    const token = localStorage.getItem('authToken');

    const headers = {
        'Content-Type': 'application/json',
        ...(token && { 'Authorization': `Bearer ${token}` }),
        ...(options.headers)
    };

    const response = await fetch(`${BASE_URL}${endpoint}`, {
        ...options,
        headers
    });

    if (response.status === 401) {
        localStorage.removeItem('authToken');
        window.location.href = '/login';
        throw new Error('Unauthorized. Please log in again.');
    }

    return response
}