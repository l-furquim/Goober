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
       <nav className="flex flex-col gap-4   mt-5 ml-5 rounded-t-xl mr-5
     text-zinc-300 text-xl bg-zinc-900">
        <div className="flex flex-row gap-4 flex-grow">
            <Link href="/dashboard/home">
                <span className="flex-row flex gap-3 ml-20 mt-9 hover:opacity-75 transition">

                    <h1 className="uppercase">Goober</h1>
                    <ShoppingCartIcon/>    
                </span> 
            </Link>

                <span className="flex flex-grow justify-center ml-60 items-center gap-3 mt-8" >

                    <SearchBar/>

                </span>

                <span className="flex flex-grow justify-center mt-8 absolute right-72 items-center">
                    <NewAnnouncement/>
                </span>

                <span className="flex flex-grow justify-center items-center gap-3 mt-8 hover:opacity-75 transition">

                    <UserData/>

                </span>
        </div>
       </nav>

        <div className="z-10">
            <FilterBar/>
        </div>  
    </>
    
    )

}