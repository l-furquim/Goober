'use client'

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useRouter } from "next/navigation";
import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import {zodResolver} from "@hookform/resolvers/zod"
import {ShoppingCartIcon } from "lucide-react";




const RegisterFormShema = z.object({
        email: z.string().email({message: "Email invalido"}),
        password: z.string().min(1, "Senha invalida"),
        confirmPassword: z.string(),
        iconImage: z.unknown()
    }).refine(data => data.password === data.confirmPassword, {
        path: ['confirmPassword'],
        message: "As senhas não são iguais"
    });

    type RegisterFormType = z.infer<typeof RegisterFormShema>;

    export default function RegisterPage(){

        const [message, setMessage] = useState<React.ReactNode>(<></>);

        const [showCodeVerifier, setShowCodeVerifier] = useState(false);

        const router = useRouter();

        const {handleSubmit, register,setValue} = useForm<RegisterFormType>({
            resolver: zodResolver(RegisterFormShema),
            defaultValues: {
                email: "",
                password: "",
                confirmPassword: "",
                iconImage: null,
            }
        });

        const handleRegisterSubmit = async(data: RegisterFormType) => {

            const {email, password, confirmPassword} = data;

            const jsonData =  JSON.stringify({email, password, confirmPassword});

        }
    
    

    return (
    <>
     <nav className="flex flex-row gap-4 items-center pb-20 mt-5 ml-5 rounded-xl mr-5
     space-x-5 uppercase text-zinc-300 justify-center text-xl bg-zinc-900">
               Goober<ShoppingCartIcon className="text-zinc-300"/>
    </nav>
            
        <div className="flex items-center justify-center min-h-screen ">
            
            <div className="container flex flex-col gap-10 bg-zinc-700 max-w-prose rounded-xl p-10 border-zinc-900 border-solid border-4">

                <h2 className="font-bold text-xl">Insira seus dados de registro:</h2>

                {message}


                <form className="space-y-2" onSubmit={handleSubmit(handleRegisterSubmit)}>
                    
                    <label className="block text-zinc-300">Email: </label>
                        <Input className="border-zinc-900 border-2 bg-zinc-600" {...register("email")} type="text"/>

                    <label className="block text-zinc-300">Senha: </label>
                        <Input className="border-zinc-900 border-2 bg-zinc-600" {...register("password")} type="text"/>
                    
                    <label className="block text-zinc-300">Confirme sua senha: </label>
                        <Input className="border-zinc-900 border-2 bg-zinc-600" {...register("confirmPassword")} type="text"></Input>

                    <label className="block text-zinc-300">Sua foto de perfil:</label>
                        <Input className="border-zinc-900 border-2 bg-zinc-600" {...register("iconImage")} type="file"></Input>


                    <Button type="submit">Enviar</Button>

                </form>
            </div>
        </div>
    </>
    )

    }
