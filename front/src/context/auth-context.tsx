'use client'

import { deleteCookie, getCookie, setCookie } from "cookies-next";
import { createContext } from "react";


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

        console.log(token);

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