'use client'

import type { GetUserDataResponseType } from "@/app/view/user/[user]/page";
import { backEndApi } from "@/lib/api";
import type { AxiosError } from "axios";
import { deleteCookie, getCookie, setCookie } from "cookies-next";
import { createContext, useState } from "react";

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
    recoveryToken: () => String | undefined,
    getUserData: () => GetUserDataResponseType | undefined;
}



export const AuthContext = createContext({} as authContextType);

export function AuthContextProvider({children}: {children: React.ReactNode}){
    const [userData, setUserData]  = useState<GetUserDataResponseType>();
    
    
    var isAuthenticated = !!recoveryToken();

    async function signIn(token: String){
        setCookie("goober-auth", token, {
            maxAge: 60 * 60 * 3 
        });
        try{
            const data = await backEndApi.get("user/get", {
                headers: {
                    "Authorization": `Bearer ${token}`
                },
            });
            if(data.data){
                setUserData(data.data as GetUserDataResponseType)
            }

        }catch(e){
            const axiosError = e as AxiosError;
            console.log(axiosError);    
        }
    }

    function signOut(){

        deleteCookie("goober-auth");

    }

    function recoveryToken(){
        const cookie = getCookie("goober-auth");

        const token = cookie?.toString();

        return token;
    }
    function getUserData(){
        console.log(userData);
        return userData;
    }

    return (
        <AuthContext.Provider value={{isAuthenticated, signIn, signOut, recoveryToken, getUserData}}>
            {children}
        </AuthContext.Provider>
    )
}