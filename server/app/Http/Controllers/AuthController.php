<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Hash;

class AuthController extends Controller
{
    public function login(Request $request) {
        $validated = $request->validate([
            "email" => "string|required",
            "password" => "string|required"
        ]);

        $user = User::where("email", $validated['email'])->get()->first();

        if(!$user || !Hash::check($validated['password'], $user->password)) {
            return response()->json([
                "message" => "Invalid Credentials",
            ], Response::HTTP_NOT_ACCEPTABLE);
        }

        $token = $user->createToken("login_token")->plainTextToken;
        return response()->json([
            "message" => "Login Success",
            "token" => $token,
            "user" => $user
        ], Response::HTTP_OK);
    }
}
