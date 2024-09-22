'use client'

import { NavBar } from "@/app/dashboard/_components/nav-bar";
import { CustomAlert, CustomAlertType } from "@/components/alert/Alert";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { backEndApi } from "@/lib/api";
import { zodResolver } from "@hookform/resolvers/zod";
import { RocketIcon } from "lucide-react";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";

export type NewAnnouncementRequestType = {
    announcementName: String,
    annoncementDescription: String,
    announcementPrice: Number
};



const NewAnnouncementPage = ({params,} : {
    params: {user: String};
}) => {

    const NewAnnouncementFormSchema = z.object({
        announcementName: z.string().min(3,"Nome inválido"),
        announcementDescription: z.string().min(5, "Descrição inválida"),
        announcementPrice: z.number()
    });
    


    type NewAnnouncementFormType = z.infer<typeof NewAnnouncementFormSchema>;

    const userCookies = params.user;
    const [announcementImages , setAnnoncementImages] = useState<File[]>([]);
    const [message, setMessage] = useState(<></>);
    const router = useRouter();


    const {handleSubmit, register} = useForm<NewAnnouncementFormType>({
        resolver: zodResolver(NewAnnouncementFormSchema),
        defaultValues: {
            announcementName: "",
            announcementDescription: "",
            announcementPrice: 0
        },
    });

    const handleAnnouncementFile = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files && e.target.files.length > 0) {
            const images = Array.from(e.target.files);
            setAnnoncementImages(images)
        }
    }

    const handleNewAnnouncementSubmit = async(data: NewAnnouncementFormType) =>{

        const {announcementName, announcementDescription , announcementPrice} = data;

        const form = new FormData();        

        const announcementJson: NewAnnouncementRequestType = {
            announcementName: announcementName,
            annoncementDescription: announcementDescription,
            announcementPrice: announcementPrice
        };

        form.append("announcementJson", new Blob([JSON.stringify(announcementJson)], { type: 'application/json' }))
        
        if(announcementImages){
           announcementImages.forEach(image => form.append("announcementImages", image));
        }

        form.forEach((item => console.log(item)));
        
        try{    

            const response = await backEndApi.post("announcement/create", form);

            if(response.data){
                setMessage(<CustomAlert title="Sucesso" msg="anúncio realizado com sucesso !" 
                                        type={CustomAlertType.SUCESS}/>)
                setTimeout(()=> router.push("/dashboard/user/profile"));           
            }
        

        }catch(e){


        }

    }   



    return (

        
    <>
    <NavBar/>
        <div className="container flex items-center justify-center">
            <div className="flex-col mt-10 text-zinc-300 container p-16 rounded-xl justify-center w-[600px] h-[700px] bg-zinc-900">
                    
                    {message}

                    <form className="mt-3" onSubmit={handleSubmit(handleNewAnnouncementSubmit)} encType="multipart/form-data">
                        <h1 className="text-2xl mb-10">Insira as informações do anuncio: </h1> 
                          <div className="space-y-8">  
                                
                                <div className="space-y-3">
                                    <p>Nome do produto: </p>
                                        <Input {...register("announcementName")} className="bg-slate-800 border-slate-900 focus-visible:ring-transparent"
                                        placeholder="Diga algo que chame atenção.."/>
                                </div>

                                <div className="space-y-3">
                                    <p>Descrição do produto: </p>
                                        <Input {...register("announcementDescription")} className="bg-slate-800 
                                        border-slate-900 focus-visible:ring-transparent"
                                            placeholder="Mouse bluetooth 5.0 ultra led HD"/>
                                </div>

                            <div className="space-y-3">
                                <p>Preço do produto: </p>
                                
                                    <p className="flex flex-row gap-2">  
                                     <Input {...register("announcementPrice")} className="bg-slate-800  border-slate-900 focus-visible:ring-transparent"
                                            placeholder="R$400.69" />
                                    </p>

                            </div>
                            
                            <div className="space-y-3">
                                <p>Imagens do produto: </p>
                                        <Input className="bg-slate-800 border-slate-900 focus-visible:ring-transparent"
                                        type="file" onChange={handleAnnouncementFile } accept="image/*" multiple/>

                            </div>
                            
                            <div>
                                <p className="justify-center items-center flex">
                                    <Button className="bg-slate-800 gap-3 hover:bg-zinc-700"type="submit">
                                            Anunciar 
                                            <RocketIcon size={"15px"}/>
                                    </Button>
                                </p>
                            </div>
                            
                        </div>
                    </form>

            </div>
        </div>
    </>
    )

}

export default NewAnnouncementPage;