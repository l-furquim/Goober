'use client'

import { CustomAlert, CustomAlertType } from "@/components/alert/Alert";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { AuthContext } from "@/context/auth-context";
import { backEndApi } from "@/lib/api";
import { zodResolver } from "@hookform/resolvers/zod";
import { AxiosError } from "axios";
import { ShoppingCartIcon } from "lucide-react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import React from "react";
import { useContext, useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod"


    const LoginFormSchema = z.object({
        email: z.string().email({message: "Email invalido"}),
        password: z.string().min(1,"Senha invalida")
    });

    type loginFormType = z.infer<typeof LoginFormSchema>;

    export default function LoginPage(){

        const [message, setMessage] = useState<React.ReactNode>(<></>);

        const router = useRouter();

        const context = useContext(AuthContext);

        const {handleSubmit, register, setValue} = useForm<loginFormType>({
            resolver: zodResolver(LoginFormSchema),   
            defaultValues: {
                email :"",
                password: ""
            }
        });

        const handleLoginSubmit = async(data: loginFormType) =>{

            const {email, password} = data;

            try{

                const response = await backEndApi.post("user/login", JSON.stringify({email,password}));

                if(response.data){

                    const {token} = response.data;
                    
                    context.signIn(token);
                    
                    setTimeout(()=> router.push("/dashboard/home"), 500);
                }


            }catch(e){
                const axiosError = e as AxiosError;

                <CustomAlert type={CustomAlertType.ERROR} title="Erro" 
                msg={axiosError.message} ></CustomAlert>
            }


        }

        
        return(

            <>
            <nav className="flex-row flex justify-center container gap-4 p-10 mt-5 ml-5 rounded-xl mr-5
            space-x-5 uppercase text-zinc-300 text-xl border-muted-foreground border-zinc-300 border-[0.5px] bg-zinc-950">
                      
                    <span className=" flex items-center">
                        <h1>Goober</h1>
                        
                        <ShoppingCartIcon className="text-zinc-300"/>
                    </span>
           
           </nav>
                   
               <div className="flex items-center justify-center min-h-screen ">
                   
                   <div className="container flex flex-col gap-10 bg-zinc-950 max-w-prose rounded-xl p-10 border-zinc-300 border-muted-foreground border-[1px]">

                   <h2 className="font-bold text-xl text-zinc-300">Insira seus dados de login:</h2>

                   <span className="text-zinc-300 underline  text-muted-foreground text-sm">
                        <a href="/register">Não tem uma conta?</a>
                    </span>

                        {message}
       
                       <form className="space-y-2" onSubmit={handleSubmit(handleLoginSubmit)}>
                           
                           <label className="block text-zinc-300">Email: </label>
                               <Input className="border-zinc-300 text-center text-zinc-300 border-muted-foreground border-[1px] bg-zinc-900" {...register("email")} type="text"/>
       
                           <label className="block text-zinc-300">Senha: </label>
                               <Input className="border-zinc-300 text-center text-zinc-300 border-muted-foreground border-[1px] bg-zinc-900" {...register("password")} type="password"/>
       
                           <Button className="bg-zinc-300 text-black hover:bg-zinc-400" type="submit">Enviar</Button>
       
                       </form>
                   </div>
               </div>
           </>
        )

    }