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
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
  } from "@/components/ui/select"


export type NewAnnouncementRequestType = {
    announcementName: String,
    annoncementDescription: String,
    announcementPrice: Number,
    announcementCategorie: String,
    announcerToken: String
};



const NewAnnouncementPage = ({params,} : {
    params: {user: String};
}) => {

    const NewAnnouncementFormSchema = z.object({
        announcementName: z.string().min(3,"Nome inválido"),
        announcementDescription: z.string().min(5, "Descrição inválida"),
        announcementPrice: z.string(),
        announcementCategorie: z.string()
    });
    


    type NewAnnouncementFormType = z.infer<typeof NewAnnouncementFormSchema>;

    const userCookies = params.user;
    const [announcementImages , setAnnoncementImages] = useState<File[]>([]);
    const [message, setMessage] = useState(<></>);
    const router = useRouter();


    const {handleSubmit, register, setValue} = useForm<NewAnnouncementFormType>({
        resolver: zodResolver(NewAnnouncementFormSchema),
        defaultValues: {
            announcementName: "",
            announcementDescription: "",
            announcementPrice: "",
            announcementCategorie : "",
        },
    });

    const handleAnnouncementFile = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files && e.target.files.length > 0) {
            const images = Array.from(e.target.files);
            setAnnoncementImages(images)
        }
    }

    const handleNewAnnouncementSubmit = async(data: NewAnnouncementFormType) =>{

        const {announcementName, announcementDescription , announcementPrice, announcementCategorie} = data;

        const form = new FormData();        

        const announcementJson: NewAnnouncementRequestType = {
            announcementName: announcementName,
            annoncementDescription: announcementDescription,
            announcementPrice: parseFloat(announcementPrice),
            announcementCategorie: announcementCategorie.toString(),
            announcerToken: userCookies,
        };

        form.append("announcementJson", new Blob([JSON.stringify(announcementJson)], { type: 'application/json' }))
        
        if(announcementImages){
           announcementImages.forEach(image => form.append("announcementImages", image));
        }

        form.forEach((item => console.log(item)));
        console.log(announcementCategorie);
        
        try{    

            const response = await fetch('http://localhost:8080/announcement/create', {
                method: 'POST',
                body: form, 
                headers: {
                    'Authorization': `Bearer ${userCookies}`
                }
              });

            if(response.ok){
                setMessage(<CustomAlert title="Sucesso" msg="anúncio realizado com sucesso !" 
                                        type={CustomAlertType.SUCESS}/>)
                setTimeout(()=> router.push("/dashboard/user/profile"), 2000);           
            };

        
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

                            <div>
                                <p>Categoria do produto:</p>

                                <Select onValueChange={(value) => setValue("announcementCategorie", value)}>
                                    <SelectTrigger className="bg-slate-800 border-slate-900" id="categorie">
                                        <SelectValue placeholder="Escolher" />
                                    </SelectTrigger>
                                    <SelectContent position="popper" className="bg-slate-900  text-zinc-300 border-slate-900 ">
                                        <SelectItem value="GAMER">Gamer</SelectItem>
                                        <SelectItem value="CASA">Casa</SelectItem>
                                        <SelectItem value="ROUPAS E ACESSORIOS">Roupas e Acessórios</SelectItem>
                                        <SelectItem value="ELETRONICOS">Eletrônico</SelectItem>
                                        <SelectItem value="SAUDE E BELEZA">Saúde e Beleza</SelectItem>
                                        <SelectItem value="INFANTIL">Infantil</SelectItem>
                                        <SelectItem value="ESPORTE">Esporte</SelectItem>
                                        <SelectItem value="COMIDA">Comida</SelectItem>
                                        <SelectItem value="UTEIS">Úteis</SelectItem>  {/* Agora dentro do SelectContent */}
                                    </SelectContent>
                                </Select>
                            </div>

                            
                            <div className="space-y-3">
                                <p>Imagens do produto: </p>
                                        <Input className="bg-slate-800 border-slate-900 focus-visible:ring-transparent"
                                        type="file" onChange={handleAnnouncementFile } accept="image/*" multiple/>

                            </div>
                            
                            <div>
                                <p className="justify-center items-center flex">
                                    <Button className="bg-slate-800 gap-3 hover:bg-zinc-700" type="submit">
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