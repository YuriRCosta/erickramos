import React from "react";
import LoginForm from "@/components/LoginForm";

export default function Home() {

  return (
      <div className="relative flex flex-col items-center justify-center min-h-screen overflow-hidden">
          <div className="w-full p-6 bg-white rounded-md shadow-md lg:max-w-xl">
              <h1 className="text-3xl font-bold text-center text-gray-700">Erick Ramos - Retifica de Cabecotes</h1>
              <LoginForm />
          </div>
      </div>
  );
}
