import { FilterBar } from "./filter-bar"
import Link from "next/link"
import { SearchBar } from "./search-bar"
import { UserData } from "./user-label"
import { ShoppingCartIcon } from "lucide-react"
import NewAnnouncement from "./new-announcement"
import React from "react"
import { ThemeButton } from "@/components/theme/theme-button"

export const NavBar = () => {   

    const showCategories = () => {

        return <h1>Oi</h1>

    }
    

    return (
        <>
        <div className="w-screen flex flex-row items-center justify-center">
            <nav className="flex w-screen items-center justify-center gap-20 m-5 pb-14 rounded-xl text-zinc-300 text-xl bg-zinc-950 border border-muted-foreground">
                
                <Link href="/dashboard/home">
                    <div className="flex ml-10 gap-3 mt-9 hover:opacity-75 transition">
                        <h1 className="uppercase">Goober</h1>
                        <ShoppingCartIcon />
                    </div>
                </Link>
    
                <div className="mt-8 flex flex-grow justify-center mr-32">
                    <SearchBar />
                </div>

    
                <div className="flex flex-col mr-8 absolute right-14 gap-4 mt-10">
                    <UserData/>
                    <NewAnnouncement />
                </div>
            </nav>
        </div>
    
    </>
    
    
    )

}