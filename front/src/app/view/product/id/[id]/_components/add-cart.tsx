'use client'
import { Button } from "@/components/ui/button"
import {useContext, useState } from "react";

import type { AnnouncementType } from "../page";
import { getCookie } from "cookies-next";
import { AuthContext } from "@/context/auth-context";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuPortal,
  DropdownMenuSeparator,
  DropdownMenuShortcut,
  DropdownMenuSub,
  DropdownMenuSubContent,
  DropdownMenuSubTrigger,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { PlusCircleIcon, CheckIcon, XCircleIcon } from "lucide-react";
import { backEndApi } from "@/lib/api";
import { headers } from "next/headers";



const AddCart: React.FC<AnnouncementType> = ({announcement}) => {
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(<PlusCircleIcon/>);
  const context = useContext(AuthContext);

  const handleAddCart = async(event: React.MouseEvent<HTMLButtonElement>) => {
    
    event.stopPropagation();

    setLoading(true)

    const cookie = getCookie("goober-auth");

    const data = await context.getUserData();
  

    if(data){
      try{
        const CreateCartRequestDto = {
          productList: announcement.products.map(product => ({
            productId: product.productId,
            productName: product.productName,
            productPrice: product.productPrice,
            productDescription: product.productDescription,
          }))
        };
        const response = await backEndApi.post(`cart/create/${data.userId}`, JSON.stringify(CreateCartRequestDto), {
          headers: {
            "Authorization": `Bearer ${cookie}`
          },
        });
        if(response.data){
            setTimeout(()=> {
            setLoading(false);
            setMessage(<CheckIcon className="text-green-600"/>);
            }, 2000)
        }
  
      } catch(e){
        setMessage(<XCircleIcon className="text-red-600"/>)
      }
    }

  }
  
  return (
    <div>   
    <DropdownMenu>
      <DropdownMenuTrigger>
        <Button className="bg-zinc-300 text-black hover:bg-zinc-400">Adicionar a um carrinho</Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent>
        <DropdownMenuLabel>Carrinhos</DropdownMenuLabel>
        <DropdownMenuSeparator />
        <DropdownMenuItem className="flex gap-3 hover:bg-zinc-950">
            Carrinho 1 
            <Button size={"icon"} onClick={handleAddCart} disabled={loading} 
                   className="bg-zinc-300 text-black hover:bg-zinc-400">
            {loading ? "..." : message}
            </Button> 
        </DropdownMenuItem>
        <DropdownMenuItem className="flex gap-3">
            Carrinho 2 
            <Button size={"icon"} className="self-end">
              <PlusCircleIcon/>
            </Button> 
        </DropdownMenuItem>
        <DropdownMenuItem className="flex gap-3" >
            Carrinho 3 
            <Button size={"icon"} className="self-end">
              <PlusCircleIcon/>
            </Button> 
        </DropdownMenuItem>
        <DropdownMenuItem className="flex gap-3">
            Carrinho 4 
            <Button size={"icon"} className="self-end">
              <PlusCircleIcon/>
            </Button> 
        </DropdownMenuItem>
        <DropdownMenuItem>
          <Button>Ver todos</Button>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  </div>
  )

}

export default AddCart;