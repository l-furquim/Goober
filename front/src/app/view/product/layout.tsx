import { Metadata } from "next";
import ProductContainer from "./page";


export const metadata: Metadata = {
    title: "Goober-Produto",
    description: "Pagina de busca de anúncios",
  };

export default function ProductContainerLayout() {
    return (
        <ProductContainer/>
    )
}