import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { NavBar } from "./dashboard/_components/nav-bar";
import { AuthContextProvider } from "@/context/auth-context";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Goober",
  description: "a e-commerce",
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
      </body>
    </html>
  </AuthContextProvider>
  );
}
