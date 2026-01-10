// zustand based store for authentication state management

import { create } from "zustand";

interface AuthState {
    token: string | null;
    role: string | null;
    login: (token: string, role: string) => void;
    logout: () => void;
}

export const useAuthStore = create<AuthState>((set) => ({
    token: typeof window !== 'undefined' 
            ? localStorage.getItem('authToken') 
            : null,
    role: null,
    login: (token: string, role: string) => {
        localStorage.setItem('authToken', token);
        set({ token, role });
    },
    logout: () => {
        localStorage.removeItem('authToken');
        set({ token: null, role: null });
    }
}));