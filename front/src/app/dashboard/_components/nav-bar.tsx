import { FilterBar } from "./filter-bar"
import Link from "next/link"
import { SearchBar } from "./search-bar"
import { UserData } from "./user-data"
import { ShoppingCartIcon } from "lucide-react"
import NewAnnouncement from "./new-announcement"

export const NavBar = () => {   

    const showCategories = () => {

        return <h1>Oi</h1>

    }
    

    return (
        <>
        <div className="container w-screen flex flex-row items-center justify-center">
            <nav className="flex w-screen items-center gap-20 m-5 pb-10 rounded-xl text-zinc-300 text-xl bg-zinc-950 border border-muted-foreground">
                
                <Link href="/dashboard/home">
                    <span className="flex ml-10 gap-3 mt-9 hover:opacity-75 transition">
                        <h1 className="uppercase">Goober</h1>
                        <ShoppingCartIcon />
                    </span>
                </Link>
    
                <span className="mt-8 flex flex-grow justify-center mr-32">
                    <SearchBar />
                </span>

    
                <span className="flex flex-col mr-8 absolute right-14 gap-4 mt-8">
                    <UserData />
                    <NewAnnouncement />
                </span>
            </nav>
        </div>
    
    </>
    
    
    )

}