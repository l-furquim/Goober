'use client'

import { CustomAlert, CustomAlertType } from "@/components/alert/Alert";
import { backEndApi } from "@/lib/api";
import { AxiosError } from "axios";
import { getCookie } from "cookies-next";
import Image from "next/image";
import Link from "next/link";
import { useEffect, useState } from "react";
export type ProductProps = {
    productId: String,
    productName: String
    productPrice: String,
    productCategorie: String,
    productDescription: String,
    productImages: String
};
export type AnnouncementProps = {
    announcementId: String,
    announcementName: String,
    announcementPrice: Number,
    announcementLikes: Number,
    announcementQuestions: Number,
    announcerId: String,
    productImages: String
    products: ProductProps[],
    productQuestions: Number
};
export type GetAllPostsResponse = {
    announces: AnnouncementProps[]
};


const FeedPage = () => {


    const [announcement, setAnnouncements] = useState<AnnouncementProps[]>([]);
    const [message, setMessage] = useState(<></>);
    const userCookie = getCookie("goober-auth");


    useEffect(() => {
        const GetAnnouncements  = async() => {
            try{
                const response = await backEndApi.get("announcement/find/all", {
                    headers: {
                        'Authorization': `Bearer ${userCookie}`
                    }
                });
                
                if(response.data){
                    const announcements = response.data as GetAllPostsResponse;

                    setAnnouncements(announcements.announces);
                }
            }catch(e){
                const axiosError = e as AxiosError;
                setMessage(<CustomAlert type={CustomAlertType.ERROR} title={"Erro !"} msg={axiosError.message}/>)
            }
        }
        GetAnnouncements()

    }, [])

    const GetAnnouncements = async() => {



    }

    return (
        <div className="container flex flex-wrap justify-center ml-10 mt-10 bg-zinc-950  w-full gap-10 h-fit">
            {message}

            {announcement.map((ann) => (
            <ul
                key={ann.announcementId.toString()}
                className="flex h-72 w-1/4 mt-20 max-w-[calc(100%/3-10px)]
                rounded-xl bg-zinc-900 border border-zinc-950 border-muted-foreground list-none
                hover:border-gray-500 transition duration-400"
            >
                <div>
                    <Link href={`/view/product?name=${ann.announcementName}&price=120&category=gamer`}>
                        <li className="p-4 text-zinc-300">
                            {ann.announcementName.substring(0,35)}
                            {/* {announcementImages.map(image => (
                            <div>
                                <Image
                                    width={600} height={300} src={image.toString()}
                                    alt="Mouse gamer"
                                    style={{ transform: 'scale(0.7)' }}/>
                                <p className="text-2xl font-bold">{ann.announcementPrice.toString()}</p>
                            </div>
                            ))} */}
                            <div>
                                {ann.announcementPrice.toString()}
                            </div>
                        </li>
                    </Link>
                </div>
            </ul>
                ))}
        </div>
    );

}

export default FeedPage;