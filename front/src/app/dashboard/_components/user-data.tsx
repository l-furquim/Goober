'use client'

import { Button } from "@/components/ui/button";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";
import { UserCircleIcon, UserIcon, WalletIcon } from "lucide-react"
import Link from "next/link"
import { useRouter } from "next/navigation";
import qs from "qs";
import React from "react";
import { useState } from "react";

export const UserData = () => {

    const router = useRouter();

    const value = "Lucas";

    const [showOptions,setShowOptions] = useState(false);


    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {

        e.preventDefault();

        if(!value) return;

        const query = qs.stringify({ term: value });

        const url = `/dashboard/home/profile?${query}`;
        router.push(url);
    };

    const onClickUser = () => {
        setShowOptions(!showOptions);
    }


    return (
    <div>
    
      <div onClick={onClickUser} className="flex flex-row gap-2 hover:opacity-75 transition hover:cursor-pointer">
      
      <DropdownMenu>
          <DropdownMenuTrigger className="flex flex-row gap-2">Lucas<UserCircleIcon className="mt-[0.5px]"/> </DropdownMenuTrigger>
      
            <DropdownMenuContent>
              <DropdownMenuLabel>Minha conta</DropdownMenuLabel>
                <DropdownMenuSeparator />
                  <Link href={`/view/user/${value}`}>
                    <DropdownMenuItem className="hover:cursor-pointer"><UserIcon size={15}/> Perfil</DropdownMenuItem>
                  </Link>
                  <DropdownMenuItem><WalletIcon size={15}/> Pagamentos</DropdownMenuItem>
             </DropdownMenuContent>
        </DropdownMenu>

      </div>
    
    </div>
    )
    
}