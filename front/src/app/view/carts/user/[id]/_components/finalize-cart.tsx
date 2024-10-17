import { Button } from "@/components/ui/button";
import { ScrollArea } from "@/components/ui/scroll-area";
import { Separator } from "@/components/ui/separator";
import { Sheet, SheetClose, SheetContent, SheetDescription, SheetFooter, SheetHeader, SheetTrigger } from "@/components/ui/sheet";
import type { GetUserCartReponseType } from "@/context/auth-context";
import { MinusIcon, PlusIcon, ShoppingBasketIcon } from "lucide-react";
import { useState } from "react";

export default function FinalizeCart(cart: GetUserCartReponseType) {
  const [items, setItems] = useState(cart.itemsQuantity.toString());
  return (
    <>
     <Sheet>
        <SheetTrigger>
          <Button className="bg-zinc-300 hover:bg-zinc-400 text-black">
                Finalizar carrinho <ShoppingBasketIcon className="text-black" />  
          </Button>
        </SheetTrigger>
        <SheetContent>
          <SheetHeader>Finalizar...
          <SheetDescription>
            Alterações antes de finalizar o carrinho
          </SheetDescription>
          </SheetHeader>
          <ScrollArea className="h-80 overflow-auto">
            <div className="mt-10 flex flex-col gap-10 mb-10">
                    <div className="w-full h-full shadow-md rounded-md flex flex-col gap-2 p-5 border-muted-foreground border-[1px] border-zinc-300">
                      <p>Mouse gamer ultra raro</p>
                      
                      <p className="w-[80%] mt-5 mb-5 text-muted-foreground">Um mouse super gamer qwoejoi ejqwoiejqwejqwoie jqoeqjeo qjeqweqwoij</p>
                      
                      <Separator orientation="horizontal" className="w-full bg-zinc-300 bg-muted-foreground"/>
      
                        <div className="text-green-500  gap-4 w-full">
                          <p className="font-bold text[20px]">R$ 128,90 </p>
                          <p className="text-zinc-300 flex-row gap-5 flex justify-end">
                            <Button onClick={e => {
                              setItems(i => {
                                const currentTotalNumber = parseInt(i); // ou parseFloat(currentTotal)
                                return (currentTotalNumber + 1).toString();
                                    
                              })
                            }} size={"sm"} className="bg-zinc-300 hover:bg-zinc-400 text-black">
                              <PlusIcon size={"15px"}/>
                            </Button>
                            <p className=" font-bold text[20px]">{items.toString()}</p>
                            <Button onClick={e => {
                              setItems(i => {
                                const currentTotalNumber = parseInt(i);
                                var newTotalNumber = (currentTotalNumber - 1);
                                newTotalNumber < 1 ? newTotalNumber = 0 : newTotalNumber;
                                return newTotalNumber.toString();
                              })
                            }} size={"sm"} className="bg-zinc-300 hover:bg-zinc-400 text-black">
                              <MinusIcon size={"15px"}/>
                            </Button>
                          </p>
                        </div>
                    </div>
                    <div className="border-muted-foreground border-[1px] border-zinc-300 rounded-md p-2">
                      Total: R${cart.totalPrice.toFixed(2).toString().replace(".", ",")}
                    </div>
            </div>
          </ScrollArea>      
          <SheetFooter>
            <SheetClose asChild>
              <Button>Finalizar</Button>
            </SheetClose>
          </SheetFooter>
        </SheetContent>
     </Sheet>
    </>
  )
}