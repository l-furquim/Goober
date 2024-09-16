'use client'

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useRouter } from "next/navigation";
import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import {zodResolver} from "@hookform/resolvers/zod"
import {ShoppingCartIcon } from "lucide-react";
import { CustomAlert, CustomAlertType } from "@/components/alert/Alert";
import { backEndApi, frontEndApi } from "@/lib/api";
import { AxiosError } from "axios";

export type RegisterUserRequestType = {
    userName: string
    userEmail: string,
    userPassword: string
}


const RegisterFormShema = z.object({
        userName: z.string().min(1, "Nome invalido"),    
        userEmail: z.string().email({message: "Email invalido"}),
        userPassword: z.string().min(1, "Senha invalida"),
        confirmPassword: z.string()
    }).refine(data => data.userPassword === data.confirmPassword, {
        path: ['confirmPassword'],
        message: "As senhas não são iguais"
    });

    type RegisterFormType = z.infer<typeof RegisterFormShema>;

    export default function RegisterPage(){

        const [message, setMessage] = useState<React.ReactNode>(<></>);

        const [userImage, setUserImage] = useState<File | null>(null);

        const {handleSubmit, register,setValue} = useForm<RegisterFormType>({
            resolver: zodResolver(RegisterFormShema),
            defaultValues: {
                userName: "",
                userEmail: "",
                userPassword: "",
                confirmPassword: ""
            }
        });

        const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
            if (e.target.files && e.target.files.length > 0) {
                setUserImage(e.target.files[0]);
                console.log("Imagem selecionada:", e.target.files[0]);  // Verificação adicional
            }
        };
        
          


         
        const handleRegisterSubmit = async(data: RegisterFormType) => {

            const {userEmail, userName, userPassword} = data;

            const form = new FormData();

            const registerData: RegisterUserRequestType = {
                userEmail: userEmail,
                userName: userName,
                userPassword: userPassword
            };

            form.append("registerData", new Blob([JSON.stringify(registerData)], { type: 'application/json' }));

            if (userImage) {
                form.append("userImage", userImage);
            }
        
            form.forEach((value, key)=> (
                console.log(value, key)
            ));


            try{
                const response = await fetch('http://localhost:8080/user/register', {
                    method: 'POST',
                    body: form, 
                  });

                if(response){
                    setMessage( <CustomAlert type={CustomAlertType.SUCESS} title="Sucesso" 
                        msg="confirmação enviada no seu email" ></CustomAlert>)
                }
            }catch(e){
                const error = e as AxiosError;

                <CustomAlert type={CustomAlertType.ERROR} title="Erro" 
                msg={error.message} ></CustomAlert>
            }
        }
    
    

    return (
    <>
     <nav className="flex flex-row gap-4 items-center pb-20 mt-5 ml-5 rounded-xl mr-5
     space-x-5 uppercase text-zinc-300 justify-center text-xl bg-zinc-900">
            
            <span className=" flex items-center">
                        <h1>Goober</h1>
                        
                        <ShoppingCartIcon className="text-zinc-300"/>
            </span>
    </nav>

        <div className="flex items-center justify-center min-h-screen ">
            
            <div className="container flex flex-col gap-10 bg-zinc-700 max-w-prose rounded-xl p-10 border-zinc-900 border-solid border-4">

                <h2 className="font-bold text-xl">Insira seus dados de registro:</h2>

                {message}


                <form className="space-y-2" onSubmit={handleSubmit(handleRegisterSubmit)} encType="multipart/form-data">
                    
                    <label className="block text-zinc-300">Nome completo: </label>
                        <Input className="border-zinc-900 border-2 bg-zinc-600" {...register("userName")} type="text"/>
                    <label className="block text-zinc-300">Email: </label>
                        <Input className="border-zinc-900 border-2 bg-zinc-600" {...register("userEmail")} type="text"/>

                    <label className="block text-zinc-300">Senha: </label>
                        <Input className="border-zinc-900 border-2 bg-zinc-600" {...register("userPassword")} type="text"/>
                    
                    <label className="block text-zinc-300">Confirme sua senha: </label>
                        <Input className="border-zinc-900 border-2 bg-zinc-600" {...register("confirmPassword")} type="text"></Input>

                    <label className="block text-zinc-300">Sua foto de perfil:</label>
                        <Input className="border-zinc-900 border-2 bg-zinc-600"
                        accept="image/*" type="file" onChange={handleFileChange}></Input>


                    <Button type="submit">Enviar</Button>

                </form>
            </div>
        </div>
    </>
    )

    }
