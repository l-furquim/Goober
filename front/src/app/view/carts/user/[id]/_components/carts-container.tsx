
import type { GetUserCartReponseType } from "@/context/auth-context";
import FinalizeCart from "./finalize-cart";


export default function CartsContainer(cart: GetUserCartReponseType) {
  console.log(cart.itemsQuantity.toString() + cart.totalPrice);


  return (
    <div className="flex flex-row h-full w-full items-center bg-zinc-600 rounded-xl">
          <p className="ml-6 hover:">
              <FinalizeCart cartId={cart.cartId} totalPrice={cart.totalPrice} itemsQuantity={cart.itemsQuantity}/>
          </p>
          <p className="ml-8 flex-row flex w-full text-green-500">
           Preço total :  R$ {cart.totalPrice.toFixed(2).toString().replace(".", ",")}
          </p>
          <div className="flex w-full gap-2 justify-end mr-10">
            <p> 
                {cart.itemsQuantity.toString()}
            </p>
            <p>
                {cart.itemsQuantity == 1 ? "Item": "Items"}
            </p>
          </div>
    </div>
  )
}