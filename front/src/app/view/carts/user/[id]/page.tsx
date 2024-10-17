'use client'
import { NavBar } from "@/app/dashboard/_components/nav-bar";
import CartsContainer from "./_components/carts-container";
import { getUserDataAndCarts } from "@/lib/api";
import type { GetServerSidePropsContext } from "next";
import { useEffect, useState } from "react";
import type { GetUserCartReponseType, GetUserDataAndCartResponseType } from "@/context/auth-context";


const UserCartsPage = (context: GetServerSidePropsContext) => {
  const [carts, setCarts] = useState<GetUserCartReponseType[]>();
  
  console.log("IJQweijeqwe");

  const getData = async() => {
    const response =  await getUserDataAndCarts(context);
    
    if(response){
      setCarts(response.cartData); 
    }
  }

  useEffect(()=> {
    getData();
  },[])

  return (
  <>
    <NavBar/>


    <div className="w-full flex justify-center h-screen">
        <div className="flex w-[80%] bg-zinc-900 rounded-xl items-center  text-zinc-300 flex-col">
        <h1 className="font-bold mt-10 text-[30px]">Seus carrinhos </h1>
          
          {carts ? (
            carts.map((c) => (
              <ul className="flex flex-col items-center w-full h-full mt-5">
                <li key={c.cartId.toString()} className=" w-[70%] h-full">
                <CartsContainer cartId={c.cartId} itemsQuantity={c.itemsQuantity} totalPrice={c.totalPrice}/>
                </li>
              </ul>
            ))
          ): (
            <p>Carregando seus carrinhos...</p>
          )}
        </div>
    </div>
  </>
  ) 
}
export default UserCartsPage;