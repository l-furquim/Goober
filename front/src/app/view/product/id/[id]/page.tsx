    'use client'

import { AnnouncementProps} from "@/app/dashboard/_components/feed-page";
import { NavBar } from "@/app/dashboard/_components/nav-bar";
import { CustomAlert, CustomAlertType } from "@/components/alert/Alert";
import { backEndApi } from "@/lib/api";
import { AxiosError } from "axios";
import { getCookie } from "cookies-next";
import React, {useEffect } from "react";
import { useState } from "react";
import AnnouncerData from "./_components/announcer-data";
import AnnouncementData from "./_components/announcement-data";

export type AnnouncementType = {
    announcement: AnnouncementProps;
}

 const ProductContainer = ({params,} : {
    params: {id: String}; } )=> {
     
    const [message, setMessage] = useState(<></>);
    const [announcement, setAnnouncement] = useState<AnnouncementProps>();
    const userCookie = getCookie("goober-auth");

        useEffect(()=> {

        const getAnnouncement = async() => {
            try{

                const response = await backEndApi.get(`announcement/get/${params.id}`, {
                    headers: {
                        "Authorization": `Bearer ${userCookie}`
                    }
                });

                if(response.data){
                    const ann = response.data as AnnouncementType;
                    setAnnouncement(ann.announcement);
                }
    
            }catch(e){
                const axiosError = e as AxiosError;
                setMessage(<CustomAlert type={CustomAlertType.ERROR} title={"Erro !"} msg={axiosError.message}/>)
            }
        }
        getAnnouncement()
    }, [userCookie])


    const [selected, setSelected] = useState(false);

    const handleSwith = () => {
        setSelected(!selected);
    }

    return (
    <>
        <NavBar/>
        {message}
    <div className=" bg-zinc-900 ml-11 mr-11 mt-10 h-[2000px] items-center justify-center">
        {announcement ? (
        <>
             <AnnouncerData announcement={announcement}/>
             
            <AnnouncementData announcement={announcement}/>
             
        </>
        ): (
        <div className="flex justify-center items-center mt-64">
            <p>Carregando  o anuncio...</p>
        </div>
        ) }
    </div>
    </>
    );

};

export default ProductContainer;
