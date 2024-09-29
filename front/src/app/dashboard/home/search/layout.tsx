import { Metadata } from "next";
import SearchPage from "./page";


export const metadata: Metadata = {
    title: "Goober-Busca",
    description: "Pagina de busca de anúncios",
  };


export default function SearchPageLayout () {
    return (
        <SearchPage/>
    )
}