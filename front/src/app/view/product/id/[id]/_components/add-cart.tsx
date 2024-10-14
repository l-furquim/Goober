'use client'
import { Button } from "@/components/ui/button"
import {useContext, useState } from "react";
import type { AnnouncementType } from "../page";
import { getCookie } from "cookies-next";
import { AuthContext } from "@/context/auth-context";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { PlusCircleIcon, CheckIcon, XCircleIcon, ShoppingBasketIcon, PlusIcon, MinusIcon, MoveUpRightIcon } from "lucide-react";
import { backEndApi } from "@/lib/api";
import { toast } from "sonner";


export type CartProps = {
  name : String
};


const AddCart: React.FC<AnnouncementType> = ({announcement}) => {
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(<ShoppingBasketIcon/>);
  const [carts, setCarts] = useState<CartProps[]>([]);
  const [amount, setAmount] = useState(0);
  const context = useContext(AuthContext);

  const plusToCart = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.stopPropagation();
    
    setAmount(amount + 1);
  }
  const minusToCart = (event: React.MouseEvent<HTMLButtonElement>) => {
      event.stopPropagation();
      
      setAmount(amount - 1);
  }

  const handleNewCart = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.stopPropagation();

    const newCart: CartProps = {
      name: "Carrinho 2"
    };
    setCarts(prevItems => [...prevItems, newCart]);
  }


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
            toast("Produto adicionado ao carrinho com sucesso ✅", {
              description: "weqkepok",
              action: {
                  label: "Fechar ❌",
                  onClick: () => console.log("Fechado"),
              },
            })
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
        <DropdownMenuLabel>
          <a href="#" className="flex flex-row justify-center gap-1">
            Carrinhos <MoveUpRightIcon size={"18px"} className="mt-[1px]"/>
          </a>
        </DropdownMenuLabel>
        <DropdownMenuSeparator />
        <div className="flex gap-3 justify-center flex-col items-center">
        
          {carts.map(cart => (
          <>
            <DropdownMenuItem className="flex flex-row gap-3 hover:bg-zinc-950">
            <a href="#">{cart.name}</a> 
            <Button size={"icon"} onClick={handleAddCart} disabled={loading} 
                  className="bg-zinc-300 text-black hover:bg-zinc-400">
            {loading ? "..." : message}
            </Button> 
            <Button onClick={plusToCart} size={"icon"}>
              {amount==0? <PlusIcon/> : amount}
            </Button>
            <Button onClick={minusToCart} size={"icon"}>
              <MinusIcon/>
            </Button>
        </DropdownMenuItem>
        </>
          ))}
        <DropdownMenuItem>
          <Button className="flex gap-2" onClick={handleNewCart}>
            Novo<PlusCircleIcon/>
          </Button>
        </DropdownMenuItem>

       {/*  <DropdownMenuItem className="flex flex-row gap-3 hover:bg-zinc-950">
            <a href="#">Carrinho 2</a> 
            <Button size={"icon"} onClick={handleAddCart} disabled={loading} 
                   className="bg-zinc-300 text-black hover:bg-zinc-400">
            {loading ? "..." : message}
            </Button> 
            <Button onClick={plusToCart} size={"icon"}>
              {amount==0? <PlusIcon/> : amount}
            </Button>
            <Button onClick={minusToCart} size={"icon"}>
              <MinusIcon/>
            </Button>

        </DropdownMenuItem>
        <DropdownMenuItem className="flex flex-row gap-3 hover:bg-zinc-950">
            <a href="#">Carrinho 3</a> 
            <Button size={"icon"} onClick={handleAddCart} disabled={loading} 
                   className="bg-zinc-300 text-black hover:bg-zinc-400">
            {loading ? "..." : message}
            </Button> 
            <Button onClick={plusToCart} size={"icon"}>
              {amount==0? <PlusIcon/> : amount}
            </Button>
            <Button onClick={minusToCart} size={"icon"}>
              <MinusIcon/>
            </Button>

        </DropdownMenuItem>
        <DropdownMenuItem className="flex flex-row gap-3 hover:bg-zinc-950">
            <a href="#">Carrinho 4</a>
            <Button size={"icon"} onClick={handleAddCart} disabled={loading} 
                   className="bg-zinc-300 text-black hover:bg-zinc-400">
            {loading ? "..." : message}
            </Button> 
            <Button onClick={plusToCart} size={"icon"}>
              {amount==0? <PlusIcon/> : amount}
            </Button>
            <Button onClick={minusToCart} size={"icon"}>
              <MinusIcon/>
            </Button>

        </DropdownMenuItem> */}
       {/*  <DropdownMenuItem>
          <Button>Ver todos carrinhos</Button>
        </DropdownMenuItem> */}
        </div>
    
      </DropdownMenuContent>
    </DropdownMenu>
  </div>
  )

}

export default AddCart;