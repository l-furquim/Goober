'use client'

import { NavBar } from "@/app/dashboard/_components/nav-bar";
import { CustomAlert, CustomAlertType } from "@/components/alert/Alert";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { AxiosError } from "axios";
import { getCookie } from "cookies-next";
import React, { useContext } from "react";
import { useEffect, useState } from "react";
import ChangeEmail from "./_components/change-email";
import { AuthContext, UserDataType } from "@/context/auth-context";
import { getUserData } from "@/lib/api";
import type { GetServerSidePropsContext } from "next";


export type GetUserDataResponseType = {
    userId: String, 
    userEmail: String,
    userName: String,
    userImagePath: String
}


const UserPage = ({params}: {params: {user: String}}, context: GetServerSidePropsContext) => {
    const [message, setMessage] = useState(<></>);
    const [userData, setUserData] = useState<GetUserDataResponseType>();

    useEffect(()=> {

        const getData = async() => {
            try{
                const data = await getUserData(context);

                if(data){
                    setUserData(data);
                    return;
                }
    
            }catch(e){
                const axiosError = e as AxiosError;
                setMessage(<CustomAlert type={CustomAlertType.ERROR} title={"Erro !"} msg={axiosError.message}/>)
            }
        }
        
        getData();

    },[])


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
 
             <h1 className="text-zinc-300 font-bold">{userData.userName}</h1>
             
             <div>
                 <div className="flex flex-row gap-4 items-center">
                    
                    {userData.userEmail} 
                    
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