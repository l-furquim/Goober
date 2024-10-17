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


const AddCart: React.FC<AnnouncementType> = ({announcement}) => {
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(<ShoppingBasketIcon/>);
  const [amount, setAmount] = useState(0);
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

  const plusToCart = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.stopPropagation();
    
    setAmount(amount + 1);
  }
  const minusToCart = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.stopPropagation();
    
    setAmount(amount - 1);
  }

  const handleNewCart = async(event: React.MouseEvent<HTMLButtonElement>) => {
    const cookie = getCookie("goober-auth");
    console.log(cookie);

    event.stopPropagation();

    setLoading(true)
    if(userData){
      const AddProductToCart = {
        product: announcement.products.map(product => ({
          productId: product.productId,
          productName: product.productName,
          productPrice: product.productPrice,
          productDescription: product.productDescription,
        }))
      };
      const response = await backEndApi.post(`cart/create/${userData.userData.userId}`, 
        JSON.stringify(AddProductToCart), {
          headers: {
          "Authorization": `Bearer ${cookie}`
        },
      });

      if(response.data){
          setTimeout(()=> {
          setLoading(false);
          setMessage(<CheckIcon className="text-green-600"/>);
          toast("Produto adicionado ao carrinho com sucesso ✅", {
            description: "weqkepok",
            action: {
                label: "Fechar ❌",
                onClick: () => console.log("Fechado"),
            },
          })
          }, 2000)
        }
   
    }

    
  
  }

  const handleAddCart = async(cartId: string,event: React.MouseEvent<HTMLButtonElement>) => {
    const cookie = getCookie("goober-auth");
    event.stopPropagation();

    setLoading(true)
    if(userData){
      const AddProductToCartRequestDto = {
        products: announcement.products.map(product => ({
          productId: product.productId,
          productName: product.productName,
          productPrice: product.productPrice,
          productDescription: product.productDescription,
        }))
      };
      console.log(cartId);

      const response = await backEndApi.put(`cart/add/cart=${cartId}`, 
        JSON.stringify(AddProductToCartRequestDto), {
          headers: {
          "Authorization": `Bearer ${cookie}`
        },
      });

      if(response.data){
          setTimeout(()=> {
          setLoading(false);
          setMessage(<CheckIcon className="text-green-600"/>);
          toast("Produto adicionado ao carrinho com sucesso ✅", {
            description: `O ${
              announcement.announcementName
            } foi adicionado ao seu carrinho com sucesso !`,
            action: {
                label: "Fechar ❌",
                onClick: () => console.log("Fechado"),
            },
          })
          }, 2000)
        }
   
    }

    
    }

    
  
  return (
    <div>  
    <DropdownMenu>
      <DropdownMenuTrigger>
        <Button className="bg-zinc-300 text-black hover:bg-zinc-400">
          Adicionar a um carrinho
        </Button>
      </DropdownMenuTrigger>
    <ScrollArea>
      <DropdownMenuContent>
        <DropdownMenuLabel>
          <a href="#" className="flex flex-row justify-center gap-1">
            Carrinhos <MoveUpRightIcon size={"18px"} className="mt-[1px]"/>
          </a>
        </DropdownMenuLabel>
        <DropdownMenuSeparator />
        <div className="flex gap-3 justify-center flex-col items-center">
          {userData ? (
            userData.cartData.map(cart => (
              <DropdownMenuItem className="flex flex-row gap-3 hover:bg-zinc-950">            
                {"R$" + cart.totalPrice.toString().replace(".", ",")}
                
                {cart.itemsQuantity.toString()}
                
                <Button size={"icon"} onClick={event => {handleAddCart(cart.cartId.toString(), event)}} 
                    disabled={loading} value={cart.cartId.toString()}
                    className="bg-zinc-300 text-black hover:bg-zinc-400">
                      {loading ? "..." : message}
                </Button>
              </DropdownMenuItem> 
            
            ))
          ): (
            <span></span>
          )}
        
        <DropdownMenuItem>
          <Button className="flex gap-2" onClick={handleNewCart}>
            Novo<PlusCircleIcon/>
          </Button>
        </DropdownMenuItem>
        </div>
        <Scrollbar orientation="horizontal"/>
      </DropdownMenuContent>
      </ScrollArea>
    </DropdownMenu>
  </div>
  )

}

export default AddCart;