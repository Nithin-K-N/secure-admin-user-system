// zustand based store for authentication state management

import { decodeJwt } from "@/lib/utils/jwt";
import { create } from "zustand";

interface AuthState {
    token: string | null;
    role: string | null;
    login: (token: string, role: string) => void;
    logout: () => void;
}

export const useAuthStore = create<AuthState>((set) => {
  const token =
    typeof window !== "undefined"
      ? localStorage.getItem("authToken")
      : null;

  const decoded = token ? decodeJwt(token) : null;

  return {
    token,
    role: decoded?.role ?? null,

    login: (token: string) => {
      const decoded = decodeJwt(token);
      localStorage.setItem("authToken", token);
      set({
        token,
        role: decoded?.role ?? null,
      });
    },

    logout: () => {
      localStorage.removeItem("authToken");
      set({ token: null, role: null });
    },
  };
});