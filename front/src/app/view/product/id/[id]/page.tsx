'use client'

import { AnnouncementProps} from "@/app/dashboard/_components/feed-page";
import { NavBar } from "@/app/dashboard/_components/nav-bar";
import { CustomAlert, CustomAlertType } from "@/components/alert/Alert";
import { Button } from "@/components/ui/button";
import { backEndApi } from "@/lib/api";
import { AxiosError } from "axios";
import { getCookie } from "cookies-next";
import { HandshakeIcon, HeartIcon, MapPin, ShoppingCartIcon, Tags } from "lucide-react";
import Image from "next/image";
import { useRouter } from "next/router";
import React, { use, useEffect } from "react";
import { useState } from "react";

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
                    console.log(ann.announcement.products)
                    setAnnouncement(ann.announcement);
                }
    
            }catch(e){
                const axiosError = e as AxiosError;
                setMessage(<CustomAlert type={CustomAlertType.ERROR} title={"Erro !"} msg={axiosError.message}/>)
            }
        }
        getAnnouncement()
    }, [userCookie])

    const [imageSelected, setImageSelected] = useState();

    const [selected, setSelected] = useState(false);
    const questions = ["Faz o L"]

    const handleSwith = () => {
        setSelected(!selected);
    }

    return (
    <>
        <NavBar/>
        {message}
    <div className="container bg-zinc-900 ml-11 mt-10 h-[2000px] items-center justify-center">
        {announcement ? (
        <>
             <div className="bg-zinc-600 flex-col flex abolute items-center justify-start 
             absolute right-14 ml-8 mt-10 h-[450px] rounded-xl max-w-full w-2/5 gap-10 ">
                 
                 <p className="text-4xl flex flex-row items-center justify-center gap-3 mt-5 text-zinc-300">
                     R$ {announcement.announcementPrice.toString()} <Tags className="text-zinc-900" size={"35px"}/>
                 </p>
                 <p className="text-xl text-zinc-300"> {announcement.announcementName}</p>
                 <div className="container space-y-5 bg-zinc-900 h-40 w-80  items-center justify-normal flex flex-col rounded-xl">
                     <p className="mt-5  text-zinc-300 ">{announcement.announcerName}</p>
                     <hr className="border-1 w-32"/>
                     <p className=" text-zinc-300 flex flex-row  text-sm"><MapPin size={"20px"}/>{"Endereço"}</p>
                     <p className="text-xs mb-10 text-zinc-300 text-muted-foreground">
                     {
                     `${announcement.announcementStreet}, ${announcement.announcementNumber}-${announcement.announcementState}` 
                     }
                     </p>
                 </div>    
             
                 <div className="flex flex-row items-center justify-center gap-5">
                     <Button className="bg-zinc-300 text-black  gap-3 mt-auto hover:bg-zinc-400">
                         Comprar agora <HandshakeIcon size={"18px"} className="mt-1"/>
                     </Button>
         
                     <Button className="gap-2 bg-zinc-300 text-black hover:bg-zinc-400">
                         Adicionar ao Carrinho <ShoppingCartIcon size={"18px"}/>
                     </Button>
         
                     <HeartIcon/>
                 </div>
             </div>
                
             
             
             <div className="flex flex-row items-center">
               <div className="flex flex-col space-y-3"> 
               </div>
             
                  <Image
                     alt="morangasso"
                     src={`http://localhost:8080/announcement/get/images/src/main/resources/static/images/announcement/${encodeURIComponent(announcement.announcementName.toString())}`}
                     width={600}
                     height={300}
                     style={{ transform: 'scale(0.7)', backgroundColor: 'rgba(255, 255, 255, 0)' }}/>
             </div>
         
         
                         
                 
                 <>      
                         <div className=" flex-col containerh-[300px] flex items-center justify-start 
                         absolute left-40 ml-8 mt-40 p-10 rounded-xl max-w-full w-2/6  ">
                                 <div className="flex flex-row gap-8">
                                     <p className="container rounded-sm mt-5 bg-teal-950 text-zinc-300 ">{announcement.products.map(prod => (
                                        prod.productCategorie
                                     ))}</p>
                                 </div>
                                 
                                 <p className="flex flex-row  left-20 mt-10 text-zinc-300 text-xl">{announcement.products.map(prod => (
                                    prod.productDescription
                                 ))}</p>
                                 <hr className="border-1 mt-10 w-[600px]"></hr>
                                 
                                 <div className="flex mt-30 p-10 flex-col justify-center items-center gap-20 ">
                                         <h1 className="text-2xl font-bold text-zinc-400">Perguntas e Respostas:</h1>
                                             {questions.map((q) => (
                                                 <div className="container space-y-5 bg-white p-10 w-[600px] h-[170px] rounded-xl">
                                                     <p className="text-zinc-400">{"Lucas Furquim"}</p>
                                                     <p>{q}</p>
                                                 </div>
                                             ))}
                                 </div>
                                       
                         </div> 
                 </>    
        </>
        ): (
            <p>Carregando  o anuncio...</p>
        ) }
    </div>
    </>
    );

};

export default ProductContainer;
