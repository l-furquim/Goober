'use client'

import { backEndApi } from "@/lib/api";
import { deleteCookie, getCookie, setCookie } from "cookies-next";
import { createContext } from "react";

export type UserDataType = {
    userId: String,
    userName: String,
    userEmail: String,
    userPassword:String,
    userImage: String,
    status: String 
}


type authContextType = {
    isAuthenticated :  boolean,
    signIn: (token: String) => void,
    signOut: () => void,
    recoveryToken: () => String | undefined;
}

export const AuthContext = createContext({} as authContextType);

export function AuthContextProvider({children}: {children: React.ReactNode}){
    var isAuthenticated = !!recoveryToken();

    function signIn(token: String){
        setCookie("goober-auth", token, {
            maxAge: 60 * 60 * 3 
        });

    }

    function signOut(){

        deleteCookie("goober-auth");

    }

    function recoveryToken(){
        const cookie = getCookie("goober-auth");

        const token = cookie?.toString();

        return token;
    }


    return (
        <AuthContext.Provider value={{isAuthenticated, signIn, signOut, recoveryToken}}>
            {children}
        </AuthContext.Provider>
    )
}