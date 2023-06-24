'use client'

import React, { useState } from 'react';
import Api from "../services/api";
import {useRouter} from "next/navigation";

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const router = useRouter();

    async function handleLogin(e) {
        e.preventDefault();

        const data = {
            username,
            password
        };

        try {
            const response = await Api.post('auth/signin', data);
            localStorage.setItem('username', username);
            localStorage.setItem('accessToken', response.data.accessToken);
            console.log(response.data);
            router.push('/retentorComando')
        } catch (e) {
            alert('Falha no login, tente novamente.');
        }
    }

    return (
        <form className="mt-6" onSubmit={handleLogin}>
            <div className="mb-4">
                <label htmlFor="username" className="block text-sm font-semibold text-gray-800">
                    Usuario
                </label>
                <input type="username" value={username} onChange={handleUsernameChange} id={username} className="block w-full px-4 py-2 mt-2 text-gray-700 bg-white border rounded-md focus:border-gray-400 focus:ring-gray-300 focus:outline-none focus:ring focus:ring-opacity-40"/>
            </div>
            <div className="mb-2">
                <label htmlFor="password" className="block text-sm font-semibold text-gray-800">
                    Senha
                </label>
                <input type="password" id={password} value={password} onChange={handlePasswordChange} className="block w-full px-4 py-2 mt-2 text-gray-700 bg-white border rounded-md focus:border-gray-400 focus:ring-gray-300 focus:outline-none focus:ring focus:ring-opacity-40"/>
            </div>
            <div className="mt-2">
                <button className="w-full px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-gray-700 rounded-md hover:bg-gray-600 focus:outline-none focus:bg-gray-600">
                    Entrar
                </button>
            </div>
        </form>
    );
};

export default LoginForm;
