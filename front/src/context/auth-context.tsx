'use client'

import { UserData } from "@/app/dashboard/_components/user-data";
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
};

export type GetUserCartReponseType = {
    cartId: String,
    itemsQuantity: Number,
    totalPrice: Number
};

export type GetUserDataAndCartResponseType = {
    userData: GetUserDataResponseType,
    cartData: GetUserCartReponseType[]
}

type authContextType = {
    isAuthenticated :  boolean,
    signIn: (token: String) => void,
    signOut: () => void,
    recoveryToken: () => String | undefined,
    getUserData: () => Promise<GetUserDataAndCartResponseType> | undefined;
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
    async function getUserData(){
        const cookie = recoveryToken();
        try{
            const response = await backEndApi.get("user/get", {
                headers: {
                    "Authorization": `Bearer ${cookie}`
                },
            });

            

            if(response.data){
                const userData = response.data as GetUserDataResponseType;

                const userCarts = await backEndApi.get(`cart/get/${userData.userId}`, {
                    headers: {
                        "Authorization": `Bearer ${cookie}`
                    },
                });
                const finalResponse: GetUserDataAndCartResponseType = {
                    userData: userData,
                    cartData: userCarts.data as GetUserCartReponseType[]
                };

                return finalResponse;
            }

        }catch(e){
            const axiosError = e as AxiosError;
            console.log(axiosError);    
        }
        
    }

    return (
        <AuthContext.Provider value={{isAuthenticated, signIn, signOut, recoveryToken, getUserData}}>
            {children}
        </AuthContext.Provider>
    )
}