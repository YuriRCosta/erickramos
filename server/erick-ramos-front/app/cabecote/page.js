'use client'

import React, {useEffect, useState} from "react";
import ModalCabecote from "../../components/ModalCabecote";
import Api from "../../services/api";
import Navbar from "../../components/Navbar";

export default function Cabecote() {
    const [cabecotes, setCabecotes] = useState([]);

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const adicionarCabecote = (novoSelo) => {
        setCabecotes([...cabecotes, novoSelo]);
    };
    
    const handleSave = async (formData) => {
        try {
            const response = await Api.post('/cabecote', formData, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                },
            }); // Substitua pelo endpoint correto da sua API
            adicionarCabecote(response.data);
            closeModal();
        } catch (error) {
            console.error(error);
        }
    };

    async function fetchMoreBooks() {
        const response = await Api.get('cabecote', {
            headers: {
                Authorization: `Bearer ${accessToken}`
            },
        })
        setCabecotes(response.data);
    }

    useEffect(() => {
        fetchMoreBooks();
    }, []);

    const [isModalOpen, setIsModalOpen] = useState(false);

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    return (
        <div>
            <Navbar />
            <div className="flex items-center bg-white justify-center h-screen">
                <div className="max-w-md w-full p-6 bg-neutral-500 shadow-2xl rounded-lg shadow-md text-center">
                    <h1 className="text-2xl font-semibold mb-4">Cabecotes</h1>

                    {/* Tabela */}
                    <table className="w-full">
                        <thead>
                        <tr>
                            <th className="py-2 text-center px-4 border-b">Cabecote</th>
                            <th className="py-2 text-center px-4 border-b">Qtd Valvulas</th>
                            <th className="py-2 text-center px-4 border-b">Material</th>
                        </tr>
                        </thead>
                        <tbody>
                        {cabecotes.map((retentor) => (
                            <tr key={retentor.id}>
                                <td className="py-2 text-center px-4 border-b">{retentor.nome}</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.qtdValvulas}V</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.material}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                    <button
                        className="bg-blue-500 text-white rounded py-2 px-4 mb-4 mt-6"
                        onClick={openModal}
                    >
                        Salvar Novo
                    </button>
                    {/* ModalCabecote */}
                    {isModalOpen && (
                        <ModalCabecote closeModal={closeModal} handleSave={handleSave} />
                    )}
                </div>
            </div>
        </div>
    );
}