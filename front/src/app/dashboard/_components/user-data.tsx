'use client'

import { UserCircleIcon } from "lucide-react"
import Link from "next/link"
import { useRouter } from "next/navigation";
import qs from "qs";
import { useState } from "react";

export const UserData = () => {

    const router = useRouter();

    const value = "Lucas";

    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {

        e.preventDefault();

        if(!value) return;

        const query = qs.stringify({ term: value });

        const url = `/dashboard/home/profile?${query}`;
        router.push(url);
    };


    return (

    <>
      Lucas<UserCircleIcon/> 
    </>
    )

}