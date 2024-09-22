'use client'

import { ArrowDown } from "lucide-react"
import { useState } from "react"


export const FilterBar = () => {

    const [isHoveredCategories, setIsHoveredCategories] = useState(true);
    const [isHoveredPrice, setIsHoveredPrice] = useState(false);


    return (
    <>
        <div className="relative pb-4 flex flex-row items-center justify-center ml-5
            mr-5 bg-zinc-950 space-x-60 border-b-[1px] border-l-[1px] border-r-[1px] border-muted-foreground rounded-b-xl ">
            
            <p onMouseEnter={() => setIsHoveredCategories(true)} onMouseLeave={() =>  setIsHoveredCategories(false)} 
            className="text-sm mt-auto text-zinc-300 flex-row flex items-center justify-center text-center w-fit gap-1 cursor-pointer
            ">Categorias<ArrowDown size={"15px"}/></p>
            <p onMouseEnter={() => setIsHoveredPrice(true)} onMouseLeave={() => setIsHoveredPrice(false)} 
            className="text-sm text-zinc-300 mt-auto flex-row flex items-center justify-center text-center w-fit gap-1 cursor-pointer 
            ">Preço<ArrowDown size={"15px"}/></p>
        </div>
        <div>


        {isHoveredCategories && (
        <div className="relative flex flex-row justify-center items-center">
            <nav onMouseEnter={() => setIsHoveredCategories(true)} onMouseLeave={() =>  setIsHoveredCategories(false)} 
            className="absolute mr-72 mt-24 bg-zinc-600 w-fit rounded-b-xl text-center gap-1 z-10" >
               <ul className="p-2"> 
                        <li className="py-1 px-3 hover:bg-zinc-400">Gamer</li>
                        <li className="py-1 px-3 hover:bg-zinc-400">Bem estar</li>
                        <li className="py-1 px-3 hover:bg-zinc-400">Coco</li>
                </ul>
            </nav>
        </div>
            )}
        {isHoveredPrice && (
        <div className="relative flex flex-row justify-center items-center">
            <nav onMouseEnter={() => setIsHoveredCategories(true)} onMouseLeave={() =>  setIsHoveredCategories(false)} 
            className="absolute ml-80 mt-24 bg-zinc-600 w-fit rounded-b-xl text-center gap-1 z-10" >
            <ul className="p-2"> 
                    <li className="py-1 px-3 hover:bg-zinc-400">R$ 100,00</li>
                    <li className="py-1 px-3 hover:bg-zinc-400">R$ 200,00</li>
                    <li className="py-1 px-3 hover:bg-zinc-400">R$ 300,00</li>
            </ul>
            </nav>
        </div>
        )}

        </div>
    
    </>
    )

}