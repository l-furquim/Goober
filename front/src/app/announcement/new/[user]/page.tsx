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
import React from "react";
import { Label } from "@/components/ui/label"
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover"




export type NewAnnouncementRequestType = {
    announcementName: String,
    annoncementDescription: String,
    announcementPrice: Number,
    announcementCategorie: String,
    announcerToken: String,
    announcementStreet: String,
    announcementNumber: String,
    announcementState: String,
    announcementDistrict: String
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
    const [cep, setCep] = useState<string>("");
    const [rua, setRua] = useState<string>("");
    const [bairro, setBairro] = useState<string>("");
    const [uf, setUf] = useState<string>("");
    const [numero, setNumero] = useState<String>("");
    const [isPopoverOpen, setIsPopoverOpen] = useState(false);


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
            announcementStreet: rua,
            announcementDistrict: bairro,
            announcementNumber: numero,
            announcementState: uf
        };

        form.append("announcementJson", new Blob([JSON.stringify(announcementJson)], { type: 'application/json' }))
        
        if(announcementImages){
           announcementImages.forEach(image => form.append("announcementImages", image));
        }

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

    const limpaFormularioCep = () => {
        setRua("");
        setBairro("");
        setUf("");
      };    

    const handlePopoverClose = () => {
        setIsPopoverOpen(false);
        const endereco = {
          cep,
          rua,
          bairro,
          numero,
          uf,
    }
    console.log(numero);
    };
      
      const pesquisacep = async (valor: string) => {
 
        const cepSomenteDigitos = valor.replace(/\D/g, "");
        if (cepSomenteDigitos !== "") {
          
          const validacep = /^[0-9]{8}$/;
    
          if (validacep.test(cepSomenteDigitos)) {
            setRua("...");
            setBairro("...");
            setUf("...");
    
            try {
              const response = await fetch(`https://viacep.com.br/ws/${cepSomenteDigitos}/json/`);
              const data = await response.json();
    
              if (!("erro" in data)) {
                setRua(data.logradouro);
                setBairro(data.bairro);
                setUf(data.uf);
              } else {

                limpaFormularioCep();
                alert("CEP não encontrado.");
              }
            } catch (error) {

              limpaFormularioCep();
              alert("Erro ao buscar o CEP.");
            }
          } else {

            limpaFormularioCep();
            alert("Formato de CEP inválido.");
          }
        } else {
        
          limpaFormularioCep();
        }
      };
    



    return (

        
    <>
    <NavBar/>
        <div className=" flex items-center justify-center">
            <div className="flex text-popover-foreground flex-col mt-10 text-zinc-300 p-16 rounded-xl justify-center 
            w-[600px] h-[700px] bg-zinc-950 border border-muted-foreground shadow-md overflow-hidden border-zinc-300 ">
                    
                    {message}

                    <form className="mt-3" onSubmit={handleSubmit(handleNewAnnouncementSubmit)} encType="multipart/form-data">
                        <h1 className="text-2xl mb-10">Insira as informações do anuncio: </h1> 
                          <div className="space-y-8">  
                                
                                <div className="space-y-3">
                                    <p>Nome do produto: </p>
                                        <Input {...register("announcementName")} className="bg-zinc-900  border-zinc-300 text-zinc-300 focus-visible:ring-transparent"
                                        placeholder="Diga algo que chame atenção.."/>
                                </div>

                                <div className="space-y-3">
                                    <p>Descrição do produto: </p>
                                        <Input {...register("announcementDescription")} className="bg-zinc-900 
                                        border-zinc-300 text-zinc-300 focus-visible:ring-transparent"
                                            placeholder="Mouse bluetooth 5.0 ultra led HD"/>
                                </div>

                            <div className="space-y-3">
                                <p>Preço do produto: </p>
                                
                                    <p className="flex flex-row gap-2">  
                                     <Input {...register("announcementPrice")} className="bg-zinc-900  border-zinc-300 text-zinc-300 focus-visible:ring-transparent"
                                          placeholder="R$400.69" />
                                    </p>
                            </div>

                            <div>
                                <p>Categoria do produto:</p>

                                <Select onValueChange={(value) => setValue("announcementCategorie", value)}>
                                    <SelectTrigger className="bg-zinc-900 border-zinc-300 text-zinc-300" id="categorie">
                                        <SelectValue placeholder="Escolher" />
                                    </SelectTrigger>
                                    <SelectContent position="popper" className="bg-zinc-950  text-zinc-300 border-zinc-300 text-zinc-300 ">
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
                                        <Input className="bg-zinc-900 border-zinc-300 text-zinc-300 focus-visible:ring-transparent"
                                        type="file" onChange={handleAnnouncementFile } accept="image/*" multiple/>

                            </div>
                            <Popover open={isPopoverOpen} onOpenChange={(open)=> {
                                setIsPopoverOpen(open);
                                if(!open) handlePopoverClose();
                            }}>
                                <PopoverTrigger asChild>
                                    <Button variant="outline">Endereço</Button>
                                </PopoverTrigger>
                                <PopoverContent className="w-80">
                                    <div className="grid gap-4 text-zinc-300">
                                    <div className="space-y-2">
                                        <h4 className="font-medium leading-none">Informações</h4>
                                        <p className="text-sm text-muted-foreground">
                                        insira as informações do endereço
                                        </p>
                                    </div>
                                    <div className="grid gap-2">
                                        <div className="grid grid-cols-3 items-center gap-4">
                                        <Label htmlFor="width">CEP</Label>
                                        <Input
                                        name="cep"
                                        type="text"
                                        value={cep}
                                        size={10}
                                        maxLength={9}
                                        onBlur={(e) => pesquisacep(e.target.value)}
                                        onChange={(e) => setCep(e.target.value)}
                                        />
                                        </div>
                                        <div className="grid grid-cols-3 items-center gap-4">
                                        <Label htmlFor="rua">Rua</Label>
                                        <Input
                                            name="rua"
                                            value={rua}
                                            className="col-span-2 h-8"
                                            readOnly
                                        />
                                        </div>
                                        <div className="grid grid-cols-3 items-center gap-4">
                                        <Label htmlFor="numero">Numero</Label>
                                        <Input
                                            name="rua"
                                            className="col-span-2 h-8"
                                            type="text"
                                            onChange={(e) => setNumero(e.target.value)}
                                        />
                                        </div>
                                        <div className="grid grid-cols-3 items-center gap-4">
                                        <Label htmlFor="bairro">Bairro</Label>
                                        <Input
                                            name="bairro"
                                            className="col-span-2 h-8"
                                            type="text"
                                            readOnly
                                            value={bairro}
                                        />
                                        </div>
                                        <div className="grid grid-cols-3 items-center gap-4">
                                            <Label htmlFor="estado">Estado</Label>
                                            <Input 
                                            name="uf"
                                            className="col-span-2 h-8"
                                            readOnly
                                            type="text"
                                            value={uf}
                                            size={2}
                                        />
                                        </div>
                                    </div>
                                    </div>
                                    </PopoverContent>
                                </Popover>
                            <div>

                            </div>

                            
                            <div>
                                <p className="justify-center items-center flex">
                                    <Button className="bg-zinc-300 text-black gap-3 hover:bg-zinc-400" type="submit">
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