import type { GetAllAnnouncementsResponse } from "@/app/dashboard/_components/feed-page";
import type { GetUserDataResponseType } from "@/app/view/user/[user]/page";
import type { GetUserCartReponseType, GetUserDataAndCartResponseType } from "@/context/auth-context";
import axios, { type AxiosError } from "axios"
import { getCookie } from "cookies-next";
import type { GetServerSidePropsContext } from "next";

export const backEndApi = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json"
    }

});

export const frontEndApi = axios.create({
    baseURL: "http://localhost:3000/api",
    headers: {
        "Content-Type" : "application/json"
    }
});

export const getUserDataAndCarts = async(context: GetServerSidePropsContext) => {
    const token = getCookie("goober-auth", { req: context.req, res: context.res });
    try{
        const userResult = await getUserData(context);

        const userCarts = await backEndApi.get(`cart/get/${userResult?.userId}`, {
            headers: {
                "Authorization": `Bearer ${token}`
            },
        });
        if(userResult && userCarts){
            const finalResponse: GetUserDataAndCartResponseType = {
                userData: userResult,
                cartData: userCarts.data as GetUserCartReponseType[]
            };

            return finalResponse;
        }
    }  catch(e){
        const axiosError = e as AxiosError;
        console.log(axiosError.message);
    }
} 
export async function getUserData(context: GetServerSidePropsContext){
    const token = getCookie("goober-auth", { req: context.req, res: context.res });
    try{
        const userResult = await backEndApi.get("user/get", {
            headers: {
                "Authorization": `Bearer ${token}`
            },
        });

        if(userResult.data){
            return userResult.data as GetUserDataResponseType
        }

    }  catch(e){
        const axiosError = e as AxiosError;
        console.log(axiosError.message);
    }
} 

export async function getAnnouncements(context: GetServerSidePropsContext){
    const token = getCookie("goober-auth", { req: context.req, res: context.res });
    try{
        const response = await backEndApi.get("announcement/find/all", {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });   

        if(response.data){
            return response.data as GetAllAnnouncementsResponse
        }

    }  catch(e){
        const axiosError = e as AxiosError;
        console.log(axiosError.message);
    }
} 