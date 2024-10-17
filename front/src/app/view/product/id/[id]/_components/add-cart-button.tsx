'use client'
import { Button } from "@/components/ui/button"
import {useContext, useEffect, useState } from "react";
import type { AnnouncementType } from "../page";
import { getCookie } from "cookies-next";
import { AuthContext, GetUserDataAndCartResponseType, type GetUserCartReponseType } from "@/context/auth-context";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { PlusCircleIcon, CheckIcon, XCircleIcon, ShoppingBasketIcon, PlusIcon, MinusIcon, MoveUpRightIcon, AppleIcon } from "lucide-react";
import { backEndApi } from "@/lib/api";
import { toast } from "sonner";
import type { AxiosError } from "axios";
import { ScrollArea, Scrollbar } from "@radix-ui/react-scroll-area";
import Link from "next/link";
import CartsContainer from "./carts-container";


const AddCart: React.FC<AnnouncementType> = ({announcement}) => {
  const context = useContext(AuthContext);
  const [userData, setUserData] = useState<GetUserDataAndCartResponseType>();
  

  useEffect(() => {
    const fetchData = async() => {
      try{
        const response = await context.getUserData();

        console.log(response);

        if(response){
          setUserData(response);
        }

      }catch(e){
        const axiosE = e as AxiosError;
        console.log(axiosE.message);
      }
    } 

    fetchData();

  },[])
 

  
  return (
    <div>  
    <DropdownMenu>
      <DropdownMenuTrigger>
        <Button className="bg-zinc-300 text-black hover:bg-zinc-400">
          Adicionar a um carrinho
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent>
        <DropdownMenuLabel>
          <a href={`/view/carts/user/${userData?.userData.userId}`} className="flex flex-row justify-center gap-1">
            Carrinhos <MoveUpRightIcon size={"18px"} className="mt-[1px]"/>
          </a>
        </DropdownMenuLabel>
        <DropdownMenuSeparator />
    <ScrollArea className="h-40 overflow-auto">
        <div className="flex gap-3 justify-center flex-col items-center">
          {userData ? (
            userData.cartData.map(cart => (
            <>
             <CartsContainer announcement={announcement} carts={cart}/>
            </>               
            ))
          ): (
            <span></span>
          )}
        
       
        </div>
      </ScrollArea>
      </DropdownMenuContent>
    </DropdownMenu>
  </div>
  )

}

export default AddCart;