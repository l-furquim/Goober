import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { NavBar } from "./dashboard/_components/nav-bar";
import { AuthContextProvider } from "@/context/auth-context";
import Footer from "./dashboard/_components/footer";
import { ShoppingCartIcon } from "lucide-react";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Goober",
  description: "a e-commerce",
  icons:  {
    icon: "/shopping-cart.svg",
    shortcut: "/shopping-cart.svg",
    apple: "/shopping-cart.svg"
  }
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
  <AuthContextProvider>
    <html lang="pt-br">
      <body className={`${inter.className} bg-zinc-950`} >
          {children}
          <Footer/>
      </body>
    </html>
  </AuthContextProvider>
  );
}
