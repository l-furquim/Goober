import formidable from "formidable";
import { NextRequest , NextResponse } from "next/server";
import path from "path";
import { backEndApi } from "./lib/api";

export default async function middleware(request: NextRequest) {

    const authToken = request.cookies.get("goober-auth")?.value;

    if(authToken){
        const isTokenValid = await validateToken(authToken);
        
        if(isTokenValid){
            return NextResponse.next();
        }
    }
    return NextResponse.redirect(new URL("/register", request.url));
}

export const config = {
    matcher: ['/dashboard/home']
};

type BackEndValidateTokenResponseType = {
    isValid: boolean;
};

type BackEndValidateTokenRequestType = {
    token: String;
};

async function validateToken(token: String){
    var isValid = false;

    try{
        const response = await fetch("http://localhost:8080/user/validateToken", {
            method: "POST",
            headers: {
                "Content-Type" : "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({token} as BackEndValidateTokenRequestType)
        });
        const jsonResponse = await response.json() as BackEndValidateTokenResponseType;
        
        isValid = jsonResponse.isValid;
    }catch(e){
        isValid = false;
    }
    return isValid;


}