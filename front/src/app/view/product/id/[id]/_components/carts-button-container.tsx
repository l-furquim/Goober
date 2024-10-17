import { Button } from "@/components/ui/button"
import type { GetUserCartReponseType, GetUserDataAndCartResponseType } from "@/context/auth-context"
import { DropdownMenuItem } from "@radix-ui/react-dropdown-menu"
import { getCookie } from "cookies-next";
import { CheckIcon, ShoppingBasketIcon } from "lucide-react"
import type { AnnouncementType } from "../page";
import { useState } from "react";
import { backEndApi } from "@/lib/api";
import { toast } from "sonner";
import Link from "next/link";
import type { AnnouncementProps } from "@/app/dashboard/_components/feed-page";
import { number } from "zod";

type CartsContainerProps = {
  announcement: AnnouncementProps,
  carts: GetUserCartReponseType;
}


export default function CartButtonContainer( 
        {announcement, carts}: CartsContainerProps
    ){
  
  const [loading, setLoading] = useState(false);
  const [addMessage, setAddMessage] = useState(<ShoppingBasketIcon/>);
  const [price, setTotalPrice] = useState(carts.totalPrice.toString());

  const handleAddCart = async(cartId: string,event: React.MouseEvent<HTMLButtonElement>) => {
    const cookie = getCookie("goober-auth");
    event.stopPropagation();

    setLoading(true)
    if(cartId){
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
          setAddMessage(<CheckIcon className="text-green-600"/>);
          toast("Produto adicionado ao carrinho com sucesso ✅", {
            description: `O ${
              announcement.announcementName
            } foi adicionado ao seu carrinho com sucesso !`,
            action: {
                label: "Fechar ❌",
                onClick: () => console.log("Fechado"),
            },
          }); AddProductToCartRequestDto.products.map(p => {setTotalPrice(
            actualPrice => { const currentTotalNumber = Number(actualPrice);
                            const priceAsNumber = Number(p.productPrice);
                            return (currentTotalNumber + priceAsNumber).toString();
                          }
          )}) 
          }, 2000)
        }
   
    }

    
    }
  return (
      <DropdownMenuItem className="flex flex-row gap-3 hover:bg-zinc-950">            
      <Link href={`/view/carts/cart/${carts.cartId}`} className="border-none dec">
          {"R$" + price.replace(".", ",")}
        
          {carts.itemsQuantity.toString()}
      </Link>
      
      <Button size={"icon"} onClick={event => {handleAddCart(carts.cartId.toString(), event)}} 
          disabled={loading} value={carts.cartId.toString()}
          className="bg-zinc-300 text-black hover:bg-zinc-400">
            {loading ? "..." : addMessage}
      </Button>
    </DropdownMenuItem>
  )
}