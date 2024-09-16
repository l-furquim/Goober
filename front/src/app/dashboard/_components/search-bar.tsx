'use client'

import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { SearchIcon } from "lucide-react"
import { useRouter } from "next/navigation";
import qs from "qs";
import { useState } from "react";


export const SearchBar = () => {

    const router = useRouter();

    const [value, setValue] = useState("");

    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {

        e.preventDefault();

        if(!value) return;

        const query = qs.stringify({ term: value });

        const url = `/dashboard/home/search?${query}`;
        router.push(url);
    };



    return (
        <form className="relative w-[400px] flex items-center" onSubmit={onSubmit}>

            <Input className="bg-slate-800 border-0 focus-visible:ring-transparent" placeholder="Buscar" value={value}
            onChange={(e) => setValue(e.target.value)}/>

            <Button className="bg-zinc-900 hover:opacity-75 transition " variant={"secondary"} type="submit">
                <SearchIcon className="text-muted-foreground h-5 w-5"/>
            </Button>

        </form>
    )

}