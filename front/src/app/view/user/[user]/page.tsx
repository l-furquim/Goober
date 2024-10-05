'use client'

import { NavBar } from "@/app/dashboard/_components/nav-bar";
import { CustomAlert, CustomAlertType } from "@/components/alert/Alert";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { backEndApi } from "@/lib/api";
import { AxiosError } from "axios";
import { getCookie } from "cookies-next";
import { PencilIcon } from "lucide-react";
import { headers } from "next/headers";
import React from "react";
import { useEffect, useState } from "react";
import ChangeEmail from "./_components/change-email";


export type GetUserDataResponseType = {
    userEmail: String,
    userName: String,
    userImagePath: String
}


const UserPage = ({params}: {params: {user: String}}) => {

    const userCookie = getCookie("goober-auth");
    const [message, setMessage] = useState(<></>);
    const [userData, setUserData] = useState<GetUserDataResponseType>();

    
    useEffect(()=> {
        const getUserData = async() => {
            try{
    
                const response = await backEndApi.get("user/get", {
                    headers: {
                        'Authorization': `Bearer ${userCookie}`
                    }
                });
    
                if(response.data){
                    setUserData(response.data as GetUserDataResponseType);
                }
    
            }catch(e){
                const axiosError = e as AxiosError;
                setMessage(<CustomAlert type={CustomAlertType.ERROR} title={"Erro !"} msg={axiosError.message}/>)
            }
        }
        
        getUserData();

    },[userCookie])
    
    return (
       <>
       <NavBar/>
        {userData ? (
        <div className="flex flex-col items-center mt-28 gap-10 pb-10 pt-10  justify-center h-full text-zinc-300 border rounded-xl border-muted-foreground ">
             {message}

            <h1 className="text-3xl">Seu perfil</h1>

             <Avatar className="h-32 w-32">
                 <AvatarImage src={`http://localhost:8080/user/get/image/src/main/resources/static/images/user/${encodeURIComponent(userData.userName.toString())}`} alt={`Foto de perfil do ${userData?.userName} `}/>
                 <AvatarFallback>PFP</AvatarFallback>
             </Avatar>
 
             <h1 className="text-zinc-300 font-bold">{userData?.userName}</h1>
             
             <div>
                 <div className="flex flex-row gap-4 items-center">
                    
                    {userData?.userEmail} 
                    
                    <ChangeEmail userEmail={userData.userEmail}/>

                </div>
             </div>
 
         </div>
        ): (
            <p>Carregando suas informações...</p>
        )}   
    
       </>
    )
}
export default UserPage;