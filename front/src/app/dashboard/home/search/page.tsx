'use client'

import { backEndApi } from "@/lib/api";
import { getCookie } from "cookies-next";
import { useSearchParams } from "next/navigation";
import { useEffect, useState } from "react";
import { AnnouncementProps, GetAllAnnouncementsResponse} from "../../_components/feed-page";
import { AxiosError } from "axios";
import { CustomAlert, CustomAlertType } from "@/components/alert/Alert";
import Link from "next/link";
import React from "react";
import Image from "next/image";


const SearchPage = () => {
   
    const searchParam = useSearchParams()?.get("term");
    const [message, setMessage] = useState(<></>);
    const cookie = getCookie("goober-auth");
    const [announcements, setAnnouncements] = useState<AnnouncementProps[]>([]);

    useEffect(()=> {

        const getAnnouncementByFilter = async() => {
            try{    
                const response = await backEndApi.get(`announcement/find/search=${searchParam}`, {
                    headers: {
                        'Authorization': `Bearer ${cookie}`
                    }
                });
    
                if(response.data){
                    const announcements = response.data as GetAllAnnouncementsResponse;
                    setAnnouncements(announcements.announces);
                }
    
            }catch(e){
                const axiosError = e as AxiosError;
                setMessage(<CustomAlert type={CustomAlertType.ERROR} title={"Erro ao fazer a pesquisa!"} msg={axiosError.message}/>)
            }     
        } 
        getAnnouncementByFilter();

    }, [cookie, searchParam])


    return (
            <div className="container flex flex-wrap justify-center ml-10 mt-10 bg-zinc-950  w-full gap-10 h-fit">

            {announcements.map((ann) => (
            <ul
                key={ann.announcementId.toString()}
                className="flex h-full w-1/4 mt-20 max-w-[calc(100%/3-10px)]
                rounded-xl bg-zinc-900 border border-zinc-950 border-muted-foreground list-none
                hover:border-gray-500 transition duration-400"
            >
                <div>
                    <Link href={`/view/product?name=${ann.announcementName}&price=120&category=gamer`}>
                        <li className="p-4 text-zinc-300">
                            {ann.announcementName.substring(0,35)}
                            
                            <div>
                                <Image
                                    width={600} height={300}
                                    src={`http://localhost:8080/announcement/get/images/src/main/resources/static/images/announcement/${encodeURIComponent(ann.announcementName.toString())}`}
                                    
                                    alt="Mouse gamer"
                                    style={{ transform: 'scale(0.6)' }}/>                                                                
                                <p className="text-2xl font-bold">R$ {ann.announcementPrice.toString()}</p>

                            </div>
                        </li>
                    </Link>
                </div>
            </ul>
                ))}
        </div>

    )
}

export default SearchPage;